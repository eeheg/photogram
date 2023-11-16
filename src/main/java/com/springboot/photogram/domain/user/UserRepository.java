package com.springboot.photogram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRepository를 상속하면 IoC가 자동으로 등록됨.
public interface UserRepository extends JpaRepository<User, Integer> {
    //JPA method names
    User findByUsername(String username);  //찾아서 User 객체 리턴
}
