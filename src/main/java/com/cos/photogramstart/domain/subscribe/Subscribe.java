package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( // 유니크하게 만들기(1번이 2번 구독, 2번이 1번 구독 하는 중복상황을 막기 위해)
		uniqueConstraints = {
				@UniqueConstraint(
						name="subscribe_uk",
						columnNames = {"fromUserId", "toUserId"} // 2개를 유니크하게 만들기
				)
		}
)
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "fromUserId") // 스키마 변경: DB에서 언더바 방식(fromUser_id) 맘에 안들어서 바꿈 (이렇게 컬럼명 만들어! 니 맘대로 만들지 말고!!)
	@ManyToOne // 자동으로 테이블 생성 N : 1 (ORM 방식: entity를 받아 바로 테이블로 만들어줌)
	private User fromUser; // 구독 하는 user
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // 구독 받는 user
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}



