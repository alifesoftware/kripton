/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abubusoft.kripton.android.livedata;

import java.util.concurrent.atomic.AtomicBoolean;

import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.PageRequest;
import com.abubusoft.kripton.android.executor.KriptonTaskExecutor;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;

/**
 * A PLiveData class that can be invalidated and computed on demand.
 * <p>
 * This is an internal class for now, might be public if we see the necessity.
 *
 * @param <T>
 *            The type of the live data
 */
public abstract class KriptonPagedLiveDataHandlerImpl<T> implements LiveDataHandler {

	/** The m live data. */
	private final PagedLiveData<T> mLiveData;

	/** The m invalid. */
	private AtomicBoolean mInvalid = new AtomicBoolean(true);

	/** The m computing. */
	private AtomicBoolean mComputing = new AtomicBoolean(false);

	/**
	 * Creates a computable live data which is computed when there are active
	 * observers.
	 * <p>
	 * It can also be invalidated via {@link #invalidate()} which will result in
	 * a call to {@link #compute()} if there are active observers (or when they
	 * start observing)
	 */
	public KriptonPagedLiveDataHandlerImpl(PageRequest pageRequest) {
		mLiveData = new PagedLiveData<T>(pageRequest, this) {
			@Override
			protected void onActive() {
				KriptonTaskExecutor.getInstance().executeOnDiskIO(mRefreshRunnable);
			}
		};
	}

	/**
	 * Returns the LiveData managed by this class.
	 *
	 * @return A LiveData that is controlled by ComputableLiveData.
	 */
	@NonNull
	public PagedLiveData<T> getLiveData() {
		return mLiveData;
	}

	/** The m refresh runnable. */
	@VisibleForTesting
	final Runnable mRefreshRunnable = new Runnable() {
		@WorkerThread
		@Override
		public void run() {
			boolean computed;
			do {
				computed = false;
				// compute can happen only in 1 thread but no reason to lock
				// others.
				if (mComputing.compareAndSet(false, true)) {
					// as long as it is invalid, keep computing.
					try {
						T value = null;
						while (mInvalid.compareAndSet(true, false)) {
							computed = true;
							value = compute();
						}
						if (computed) {
							mLiveData.updateValue(value);
							// mLiveData.postValue(value);
						}
					} finally {
						// release compute lock
						mComputing.set(false);
					}
				}
				// check invalid after releasing compute lock to avoid the
				// following scenario.
				// Thread A runs compute()
				// Thread A checks invalid, it is false
				// Main thread sets invalid to true
				// Thread B runs, fails to acquire compute lock and skips
				// Thread A releases compute lock
				// We've left invalid in set state. The check below recovers.
			} while (computed && mInvalid.get());
		}
	};

	/** The m invalidation runnable. */
	// invalidation check always happens on the main thread
	@VisibleForTesting
	final Runnable mInvalidationRunnable = new Runnable() {
		@MainThread
		@Override
		public void run() {
			boolean isActive = mLiveData.hasActiveObservers();
			if (mInvalid.compareAndSet(false, true)) {
				if (isActive) {
					KriptonTaskExecutor.getInstance().executeOnDiskIO(mRefreshRunnable);
				}
			}
		}
	};

	/**
	 * Invalidates the LiveData.
	 * <p>
	 * When there are active observers, this will trigger a call to
	 * {@link #compute()}.
	 */
	@Override
	public void invalidate() {
		KriptonTaskExecutor.getInstance().executeOnMainThread(mInvalidationRunnable);
	}

	/**
	 * Compute.
	 *
	 * @return the t
	 */
	@WorkerThread
	protected abstract T compute();
}
