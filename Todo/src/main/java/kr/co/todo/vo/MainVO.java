package kr.co.todo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainVO {

	private int itemNo;
	private String content;
	private String rdate;
	private int status;
}
