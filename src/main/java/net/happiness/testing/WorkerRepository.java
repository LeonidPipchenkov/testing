package net.happiness.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Worker> findAll() {
        return jdbcTemplate.query("SELECT * FROM worker", new WorkerMapper());
    }

    public void save(Worker worker) {
        String sql = "INSERT INTO worker (name, salary) VALUES (:name, :salary)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", worker.getName())
                .addValue("salary", worker.getSalary());
        jdbcTemplate.update(sql, params);
    }

    public Optional<Worker> findById(long id) {
        String sql = "SELECT * FROM worker WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        List<Worker> workers = jdbcTemplate.query(sql, params, new WorkerMapper());
        return workers.isEmpty() ? Optional.empty() : Optional.of(workers.get(0));
    }

    public void update(Worker worker) {
        String sql = "UPDATE worker SET name = :name, salary = :salary WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", worker.getId())
                .addValue("name", worker.getName())
                .addValue("salary", worker.getSalary());
        jdbcTemplate.update(sql, params);
    }

}
