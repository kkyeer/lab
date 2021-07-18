package com.example.restservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 下午6:30 2021/7/18
 * @Modified By:
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}
