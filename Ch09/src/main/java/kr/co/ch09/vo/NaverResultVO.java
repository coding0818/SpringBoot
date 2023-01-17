package kr.co.ch09.vo;

import java.util.List;

import lombok.Data;

@Data
public class NaverResultVO {

	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private List<BookVO> items;
}
