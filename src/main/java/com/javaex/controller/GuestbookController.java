package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

//@Controller git action확인
@RestController// 선언 해줌으로써 각 기능마다 @ResponseBody 안해줘도 됨.
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	
	//저장
	//@ResponseBody -> @RestController
	@PostMapping(value="/api/guests") //주소 같으니까 get post 구분.post는 저장개
	public GuestbookVo add(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.add()");
		System.out.println(guestbookVo);
		
		GuestbookVo guestVo = guestbookService.exeAddandGuest(guestbookVo);
		System.out.println(guestVo);
		return guestVo;
	}
	//리스트
	@GetMapping(value="/api/guests")//@GetMapping)RequestMethod.GET 안써줘도 되고 import해야함
	public List<GuestbookVo> list() {
		System.out.println("GuestbookController.list()");
		
		List<GuestbookVo> guestbookList =  guestbookService.exeGuestList();
		
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	//삭제
	@DeleteMapping(value="/api/guests/{no}")
	public String remove(@RequestBody GuestbookVo guestbookVo,
						 @PathVariable(value="no") int no) {
		System.out.println("GuestbookController.remove()");
		
		guestbookVo.setNo(no);
		System.out.println(guestbookVo);
		
		//삭제
		//코드작성할것
		int count = guestbookService.exeRemove(guestbookVo);
		
		String result ="{\"count\": "+ count +"}";
		System.out.println(result);
		return result;
		}
	

}
