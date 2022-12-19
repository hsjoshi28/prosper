package com.Prosper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Prosper.entity.AnnouncementEntity;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
	
	@Query("SELECT a.announcementId FROM AnnouncementEntity a WHERE a.announcementTitle = :announcementTitle")
	Long findAnnouncementIdByAnnouncementTitle(@Param("announcementTitle") String announcementTitle);


	public List<AnnouncementEntity> findByCourseTitleAndIsApproved(String courseTitle, int isApproved);
	
	public List<AnnouncementEntity> findByIsApproved(int isApproved);
	
	public AnnouncementEntity findByAnnouncementTitle(String AnnouncementTitle);
	
	@Query("FROM AnnouncementEntity a WHERE a.announcementTitle like %:announcementTitle% AND a.isApproved = :isApproved")
	public List<AnnouncementEntity> searchByAnnouncementTitle(@Param("announcementTitle") String announcementTitle, @Param("isApproved") int isApproved);


	AnnouncementEntity findByAnnouncementId(Long announcementId);


	AnnouncementEntity findByAnnouncementTitleAndCourseTitle(String announcementTitle, String courseTitle);


}
