/**
 * By Bertrand NANCY and Kevin NUNES
 */

package pey.ph.maps.sql;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM LocationEntity")
    List<LocationEntity> getAll();

    @Insert
    void insertAll(LocationEntity... entities);
}
