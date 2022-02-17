package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	// 구독 쿼리 직접 만들기!
	// Scribe에서 생성했던 date는 작접 작성하는 쿼리에서는 작동 안 함 -> createDate 필요
	@Modifying // INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!!
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId); // 성공하면 1리턴(변경된 행의 개수가 리턴됨, 0이면? 변경된 행이 없다는 것), 실패하면 -1 리턴
	// 내가 만들었다는 의미에서 m붙임(mSubscribe)

	// 구독 취소 쿼리 직접 만들기!
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(int principalId, int pageUserId);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(int pageUserId);

}
