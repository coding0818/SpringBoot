package kr.co.ch09.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User1VO {

	private String uid;
	private String pass;
	private String name;
	private String hp;
	private String age;
}
