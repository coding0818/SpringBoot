package kr.co.farmstory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileVO {

	private int fno;
	private int parent;
	private String newName;
	private String oriName;
	private int download;
}
