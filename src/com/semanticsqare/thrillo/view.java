package com.semanticsqare.thrillo;

import com.semanticsqare.thrillo.constant.KidFreindlystatus;
import com.semanticsqare.thrillo.constant.UserType;
import com.semanticsqare.thrillo.controler.BookmarkControler;
import com.semanticsqare.thrillo.entites.BookMark;
import com.semanticsqare.thrillo.entites.User;
import com.semanticsqare.thrillo.manager.BookMarkManager;
import com.semanticsqare.thrillo.partner.Shareable;

public class view {
	
	
	public static void browse(User user, BookMark[][] bookmark) {
		System.out.println("\n"+User.getEmail()+" is browsing items....");
		
		int Bookmarkcount=0;
		for(BookMark[] bookmarklist:bookmark)
			for(BookMark bookmarks:bookmarklist)
			{
				if(Bookmarkcount<DataStore.USER_BOOKMARK_LIMIT)
				{
					boolean isbookmarked=getBookmarkDecision(bookmark);
				
					if(isbookmarked)
					{
						
						Bookmarkcount++;
					//	BookmarkControler.getInstance().saveUserBookmark(user,bookmark);
						System.out.println("new Bookmark--"+bookmarks);
					}
					
				}
				if(user.getUserType().equals(UserType.Editor)||user.getUserType().equals(UserType.Chif_Editor))
				{
					
					
					if(bookmarks.isKidfreidly()&&bookmarks.getKidFreindlystatus().equals(KidFreindlystatus.UNKNOWN));
					String kidFreindlystatus =getKidFreindlystatusDecision(bookmark);
					if(!kidFreindlystatus.equals(KidFreindlystatus.UNKNOWN))
					{
						BookmarkControler.getInstance().setkidFreindlystatus(user,kidFreindlystatus,bookmarks);
						
					}
				}
				//Sharing
				if(bookmarks.getKidFreindlystatus().equals(KidFreindlystatus.APPROVED)&&bookmarks instanceof Shareable)
						{
							boolean isShared=getShareDesicion();
							if(isShared)
							{
								BookmarkControler.getInstance().share(user,bookmarks);
							}
						}
				
			}
		
				
				
			}
		

	

	private static boolean getShareDesicion() {
		// TODO Auto-generated method stub
		return Math.random()<0.5 ? true: false;		

	}




	private static String getKidFreindlystatusDecision(BookMark[][] bookmark) {
		return Math.random()<0.4?KidFreindlystatus.APPROVED:
			(Math.random()>=0.4 && Math.random()<0.8)?KidFreindlystatus.REJECTED:KidFreindlystatus.UNKNOWN;
		
	}




	private static boolean getBookmarkDecision(BookMark[][] bookmark) {
		return Math.random()<0.5 ? true: false;		
	}

	

	

}
