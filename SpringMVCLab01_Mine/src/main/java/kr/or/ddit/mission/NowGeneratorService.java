package kr.or.ddit.mission;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class NowGeneratorService {
	public LocalDateTime receiveNow() {
		return LocalDateTime.now();
	}
}
