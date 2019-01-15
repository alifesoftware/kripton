/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.android.PagedResult;

/**
 * <p>
 * Allow to manage SQL result pages with LIMIT clause.
 * </p>
 * 
 * <p>
 * See <a href="https://www.sqlite.org/lang_select.html">here</a> for more info.
 * </p>
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <E>
 *            the element type
 */
public abstract class PagedResultImpl<E> implements PagedResult {

	/** The first row. */
	protected int firstRow;

	/** The list. */
	protected volatile List<E> list;

	/** The page size. */
	protected int pageSize;

	/**
	 * number of total element extracted by the query
	 */
	protected volatile int totalCount = -1;

	/**
	 * Instantiates a new paginated result.
	 */
	protected PagedResultImpl() {
		list = new ArrayList<>();
	}

	/**
	 * execute method
	 * 
	 * @return result
	 */
	public abstract List<E> execute();

	@Override
	public void firstPage() {
		setPage(0);

		execute();
	}

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * First row.
	 *
	 * @return the int
	 */
	public int getOffset() {
		return firstRow;
	}

	/**
	 * Get current Page
	 * 
	 * @return 0-based number of current page
	 */
	@Override
	public int getPage() {
		return firstRow / pageSize;
	}

	/**
	 * Page size.
	 *
	 * @return the int
	 */
	@Override
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Get Total count
	 */
	@Override	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Checks for next.
	 *
	 * @return true, if successful
	 */
	public boolean hasNext() {
		return list.size() > 0;
	}

	/**
	 * Next page.
	 *
	 * @return true, if successful
	 */
	@Override
	public void nextPage() {
		firstRow += pageSize;

		execute();
	}

	/**
	 * Previous page.
	 *
	 * @return true, if successful
	 */
	@Override
	public void previousPage() {
		firstRow -= pageSize;

		if (firstRow < 0) {
			firstRow = 0;
		} else {
			execute();
		}
	}

	@Override
	public void setOffset(int offset) {
		if (this.firstRow != offset && offset >= 0) {
			this.firstRow = offset;
		}
	}

	/**
	 * Goto page.
	 *
	 * @param page
	 *            the page
	 * @return true, if successful
	 */
	@Override
	public void setPage(int page) {
		firstRow = pageSize * page;

		if (firstRow < 0) {
			firstRow = 0;
			list = new ArrayList<>();
		}

	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0 && this.pageSize != pageSize) {
			this.pageSize = pageSize;
			// execute();
		}
	}
}
