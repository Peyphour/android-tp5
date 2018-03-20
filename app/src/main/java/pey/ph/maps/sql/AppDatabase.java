/**
 * By Bertrand NANCY and Kevin NUNES
 */

package pey.ph.maps.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LocationEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
}
