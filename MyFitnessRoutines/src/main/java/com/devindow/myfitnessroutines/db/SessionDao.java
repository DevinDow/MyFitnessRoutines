package com.devindow.myfitnessroutines.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.devindow.myfitnessroutines.routine.Session;

import java.util.List;

/**
 * Created by Devin on 3/18/2018.
 */

@Dao
public interface SessionDao {
	@Query("SELECT * from sessions")
	List<Session> getAll();

	@Query("SELECT * from sessions WHERE timestamp > :timestampSince")
	List<Session> getAllSinceTimestamp(long timestampSince);

	@Insert
	void insert(Session session);
}
