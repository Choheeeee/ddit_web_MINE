package kr.or.ddit.dummy.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.dummy.dao.DummyDAO;

@Service
public class DummyServiceImpl {

	@Inject
	private DummyDAO dao;
}
