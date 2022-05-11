package com.example.space_project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "top10_space")
public class Top10 {

    /**
     * id : integer, auto increment, pk
     * search_keyword : varchar(20)
     * 예) 파티룸
     * search_date : date
     * 예) 2022-05-11
     * space_id : integer
     * 예) 35965
     * space_name : varchar(200)
     * 예) [특가] 홍대파티룸 그린도어
     * created_at : datetime
     * 예) 2022-05-11 11:11:11
     * updated_at : datetime
     * 예) 2022-05-11 11:11:11
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "search_keyword")
    private String searchKeyword;

    @CreationTimestamp
    @Column(name = "searchDate")
    private LocalDate searchDate;
    @Column(name = "space_id")
    private Long spaceId;
    @Column(name="spaceName")
    private String spaceName;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Top10(String searchKeyword, Long spaceId, String spaceName) {
        this.searchKeyword = searchKeyword;
        this.spaceId = spaceId;
        this.spaceName = spaceName;
    }



}
