package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Comment;

public interface CommentService {
	public List<Comment> getComments(int num);
	public double getAvgscore(int num);
}
