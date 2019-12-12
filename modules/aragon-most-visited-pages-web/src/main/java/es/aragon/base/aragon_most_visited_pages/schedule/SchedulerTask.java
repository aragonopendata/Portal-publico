package es.aragon.base.aragon_most_visited_pages.schedule;

import es.aragon.base.aragon_most_visited_pages.util.MostVisitedPagesUtil;

public class SchedulerTask {

	public void run() {
		MostVisitedPagesUtil.addBBDD();
	}

}

