package com.iscas.rent.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	// /在线人数
	private static int online = 0;
	private static Map<Long, HttpSession> sessionList = new HashMap<Long, HttpSession>();
	public static int getOnline() {
		return online;
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		online++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		Long userid = (Long)arg0.getSession().getAttribute("userid");
		sessionList.remove(userid);
		if (online > 0)
			online--;
	}

}