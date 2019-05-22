/*
 * Copyright 2017, The Android Open Source Project
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

package sqlite.feature.custombean.case3;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

import android.arch.lifecycle.LiveData;


@BindDao(Book.class)
public interface BookDao {
/*
    @BindSqlSelect(where="id = :id")
    Book loadBookById(int id);
*/
    @BindSqlSelect(jql="SELECT * FROM Book " +            
            "WHERE isbn = :isbn"
    )
    LiveData<List<BookNoIsbn>> findBooksNoIsbn(String isbn);
/*
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    void insertBook(Book book);

    @BindSqlUpdate(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    void updateBook(Book book);

    @BindSqlDelete
    void deleteAll();*/
}
