package com.semi.task.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TaskDao {

	private Properties prop = new Properties();

	public TaskDao() {
		String path = TaskDao.class.getResource("/sql/calendar/task-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
