package by.belstu.maximchikova.bank.repository;

import by.belstu.maximchikova.bank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
