package ru.skillfactorydemo.tgbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.tgbot.entity.Income;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatsRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Income> getIncomesThatGreaterThat (BigDecimal amount){
        return jdbcTemplate.query("Select * FROM INCOMES WHERE income > ?", new IncomeRowMapper(), amount);
    }

    private static final class IncomeRowMapper implements RowMapper<Income> {
        @Override
        public Income mapRow(ResultSet rs, int rowNum) throws SQLException {
            Income income = new Income();
            income.setId((long) rs.getInt("id"));
            income.setChatId((long) rs.getInt("chat_id"));
            income.setIncome(BigDecimal.valueOf(rs.getInt("income")));
            return income;
        }
    }

}