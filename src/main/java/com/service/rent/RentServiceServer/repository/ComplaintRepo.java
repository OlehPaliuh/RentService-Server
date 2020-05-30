package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.UserComplaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepo extends CrudRepository<UserComplaint, Long> {

    List<UserComplaint> getAllByFromAccount_IdOrToAccount_Id(Long fromId, Long toId);

}
