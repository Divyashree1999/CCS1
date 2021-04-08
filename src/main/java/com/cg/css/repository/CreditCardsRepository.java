package com.cg.css.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.css.model.CreditCards;

@Repository
public interface CreditCardsRepository extends JpaRepository<CreditCards, Integer>{

	List<CreditCards> findByMinSalaryLessThanEqual(double salary);

	@Query("Select cc.period from CreditCards cc where cc.cardName = :cardName")
	int findPeriod(@Param("cardName") String cardName);

	@Query("Select cc.swipingLimit from CreditCards cc where cc.cardName = :cardName")
	double findSwipingLimit(@Param("cardName") String cardType);
	
}
