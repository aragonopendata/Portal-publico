<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.cache.SingleVMPoolUtil"%>
<%@page import="com.liferay.portal.kernel.cache.PortalCache"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="es.aragon.base.aragon.calendar.constants.CalendarConstants"%>
<%@ include file="init.jsp" %>
<%
boolean cacheEnabled = true;
//Get cache registry
String cacheRegistryKey = "calendar-aragon-" + portletDisplay.getInstanceId();
PortalCache calendarAragonPortalCache = SingleVMPoolUtil.getPortalCache("calendar-aragon-cache");
String cachedContent = null;
%>
<c:if test="<%=cacheEnabled%>">
	<%
	if (Validator.isNotNull(calendarAragonPortalCache)) {
		cachedContent = (String) calendarAragonPortalCache.get(cacheRegistryKey);
	}
	%>
</c:if>
<c:choose>
	<c:when test="<%=cachedContent != null && !cachedContent.isEmpty() %>">
		<%=cachedContent%>
	</c:when>
	<c:otherwise>
		<liferay-util:buffer var="cachedContentBuffer">
			<div class="portlet-bgblue-title" style="margin-bottom: 50px;"> 
				<h2 class="portlet-title-text portlet-title-editable" id="actualidadTitle"> 
					<span class="container"><liferay-ui:message key="es.aragon.calendar.title-calendar"/></span> 
				</h2> 
			</div>
			<div class="container">
				<div class="row row pt-4">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
						<div class="calendario-de-eventos" id="<portlet:namespace/>calendar">
							<div id="<portlet:namespace/>month_header" class="month">
								<ul>
									<li><button id="<portlet:namespace/>prev_month" tabindex="0" title="Mes previo" aria-label="Mes previo" role="button" class="prev">&#10094;</button></li>
									<li><button id="<portlet:namespace/>next_month" tabindex="0" class="next" title="Siguiente mes" aria-label="Siguiente mes" role="button">&#10095;</button></li>
									<li><button id="<portlet:namespace/>month_name" tabindex="0" class="titulo"></button></li>
								</ul>
							</div>
							<div class="weekdays" id="<portlet:namespace/>dayOfWeek_header">
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.monday.short"/></span>
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.tuesday.short"/></span>
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.wednesday.short"/></span>
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.thursday.short"/></span>
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.friday.short"/></span>
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.saturday.short"/></span>
								<span class="calendar-dayOfWeek-header"><liferay-ui:message key="es.aragon.calendar.day-of-week.sunday.short"/></span>
							</div>
							<div id="<portlet:namespace/>calendar_body">
								<div id="<portlet:namespace/>calendar_row-0" class="calendar-week-row"></div>
								<div id="<portlet:namespace/>calendar_row-1" class="calendar-week-row"></div>
								<div id="<portlet:namespace/>calendar_row-2" class="calendar-week-row"></div>
								<div id="<portlet:namespace/>calendar_row-3" class="calendar-week-row"></div>
								<div id="<portlet:namespace/>calendar_row-4" class="calendar-week-row"></div>
								<div id="<portlet:namespace/>calendar_row-5" class="calendar-week-row"></div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 ">
						<div id="<portlet:namespace/>list_event_data" class="listado-de-eventos">
							<h3 id ="<portlet:namespace/>titulo-dia" class="titulo-dia">Próximos eventos</h3>
							<section id ="<portlet:namespace/>filter_event" class="filtrado u-padding-bottom-2"> 
								<ul class="listado"> 
									<li class="col-xs-12 listado__item">
										<button id="<portlet:namespace/>filter_event_month" tabindex="0" class="name" title="Filtro mes" aria-label="Filtro mes">Mes</button> 
									</li> 
									<li class="col-xs-12 listado__item"> 
										<button id="<portlet:namespace/>filter_event_week" tabindex="0" class="name" title="Filtro semana" aria-label="Filtro semana">Semana</button> 
									</li> 
									<li class="col-xs-12 listado__item"> 
										<button id="<portlet:namespace/>filter_event_day" tabindex="0" class="name" title="Filtro día" aria-label="Filtro día">Hoy</button> 
									</li>
									<li class="col-xs-12 listado__item"> 
										<button id="<portlet:namespace/>filter_event_all" tabindex="0" class="name" title="Todos los eventos" aria-label="Todos los eventos">Todos</button>
									</li> 
								</ul> 
							</section>
							<section id="<portlet:namespace/>calendar_event_data">
								<c:choose>
									<c:when test="${events.length() != 0}">
										<div class="container">
											<c:forEach begin="0" end="4" var="index">
												<c:set var="jsonObject" value="${events.getJSONObject(index)}"></c:set>
												<c:if test='${not empty jsonObject.get("url")}'>
													<div class="row">
														<div class="col-xs-12 col-md-4 col-lg-4">
															<p class="dateEvent">${jsonObject.get("day")} ${jsonObject.get("monthName")} ${jsonObject.get("year")}</p>
														</div>
														<div class="col-xs-12 col-md-8 col-lg-8">
															<p class="contenedor-evento"><a href="${jsonObject.get('url')}" class="titulo-evento">${jsonObject.get("title")}</a></p>
														</div>
													</div>
												</c:if>
											</c:forEach>
											<c:choose>
												<c:when test="${events.length() > 5}">
													<c:forEach begin="5" end="${events.length() - 1}" var="index">
														<c:set var="jsonObject" value="${events.getJSONObject(index)}"></c:set>
														<div class="row oculto bloq-hidden">
															<div class="col-xs-12 col-md-4 col-lg-4">
																<p class="dateEvent">${jsonObject.get("day")} ${jsonObject.get("monthName")} ${jsonObject.get("year")}</p>
															</div>
															<div class="col-xs-12 col-md-8 col-lg-8">
																<p class="contenedor-evento"><a href="${jsonObject.get('url')}" class="titulo-evento">${jsonObject.get("title")}</a></p>
															</div>
														</div>	
													</c:forEach>
													<section id= "btn-view-more">
															<div class="btn-module">
																<div class="container">
																	<button id="btn-show-data" class="btn-view-more js-arrow-open collapsed" aria-expanded="false" aria-label="Mostrar eventos" data-label-closed="Mostrar eventos" data-label-opened="Ocultar datos" onclick="viewMore()">Ver más eventos</button>
																</div>
															</div>
													</section>
												</c:when>
											</c:choose>
										</div>
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="there-are-no-events"/>
									</c:otherwise>
								</c:choose>
							</section>
						</div>
					</div>
				</div>
			</div>
		</liferay-util:buffer>
		<%=cachedContentBuffer%>
		<%-- Create cache registry --%>
		<c:if test="<%=cacheEnabled%>">
			<c:if test="<%=Validator.isNotNull(calendarAragonPortalCache)%>">
				<%calendarAragonPortalCache.put(cacheRegistryKey, cachedContentBuffer, 3600);%>
			</c:if>
		</c:if>
	</c:otherwise>
</c:choose>

<script type="text/javascript">

	var <portlet:namespace/>currentMonthData;
	var status = "more";
	var strJson = '${events}';
	var jsonEvent = JSON.parse(strJson);
	/**
	 * Store required vars scope
	 *
	 * @Return required vars json
	 */
	function <portlet:namespace/>getCalendarRequiredVars() {
		return {
			namespace: '<portlet:namespace/>',
			defaultLocal: '<%=themeDisplay.getLocale().toString().replace("_", "-")%>',
			displayDayTag: 'button',
			calendarEventsResourceURL: '${events}',
			cacheServeResource: false,
			groupId: <%=themeDisplay.getScopeGroupId()%>,
			months: [
				'<liferay-ui:message key="es.aragon.calendar.month.january"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.february"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.march"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.april"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.may"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.june"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.july"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.august"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.september"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.october"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.november"/>',
				'<liferay-ui:message key="es.aragon.calendar.month.december"/>'
			],
			noEventsMessage: '<liferay-ui:message key="es.aragon.calendar.no-events-on-select-day"/>',
			eventTitleFormat: '<liferay-ui:message key="es.aragon.calendar.event-title-format"/>'
		};
	}
	/**
	 * Function showing and hidden events
	 *
	 */

	function viewMore(){
		if(status == "more"){
			$("#<portlet:namespace/>calendar_event_data .bloq-hidden").removeClass( "oculto" );
			$("#btn-show-data").text("Ver menos eventos");
			$("#btn-show-data").attr("aria-expanded","true");
			status= "less";
		}else { 
			$("#<portlet:namespace/>calendar_event_data .bloq-hidden").addClass( "oculto" );
			$("#btn-show-data").text("Ver más eventos");
			$("#btn-show-data").attr("aria-expanded","false");
			status= "more";
		}
	}

	/**
	 * Init calendar portlet on $document ready showing current month - year
	 */
	$(document).ready(function() {
		<portlet:namespace/>printCalendar(
				new Date().getFullYear(),
				new Date().getMonth(),
				<portlet:namespace/>getCalendarRequiredVars
				);
	})
	
	function <portlet:namespace/>fireIPCEvent(year, month, day, eventList, requiredVars) {
		var eventJsonDay = <portlet:namespace/>filterEventsByDay(year, month, day, <portlet:namespace/>currentMonthData);
		<portlet:namespace/>showEventsList(year, month, day, eventJsonDay, requiredVars, "day");
 		$('#<portlet:namespace/>filter_event_week').on("click", function(){ 
 			<portlet:namespace/>showEventsList(year, month, day, eventList,requiredVars, "week");
		}); 
	}
	
	/**
	 * Set previous month on click event on prev_month element based on given arguments
	 *
	 * @Input currentYear - current year number value
	 * @Input currentMonth - current month number value
	 * @Input requiredVars - required vars function reference
	 */
 	function <portlet:namespace/>setPrevMonth(currentYear, currentMonth, requiredVars) {
 		if((currentYear == new Date().getFullYear() && currentMonth > new Date().getMonth()) || (currentYear > new Date().getFullYear())){
 			$('#'.concat('<portlet:namespace/>').concat('prev_month')).show();
 			Object.prototype.hasOwnProperty.call(requiredVars, "name");
 			if(currentMonth == 0) {
 				$('#'.concat('<portlet:namespace/>').concat('prev_month')).attr('onclick', '<portlet:namespace/>printCalendar(' + (currentYear - 1) + ', 11, ' + '<portlet:namespace/>getCalendarRequiredVars' + ')');
 			} else {
 				$('#'.concat('<portlet:namespace/>').concat('prev_month')).attr('onclick', '<portlet:namespace/>printCalendar(' + currentYear + ', ' + (currentMonth - 1) + ', ' + '<portlet:namespace/>getCalendarRequiredVars' + ')');
 			}
 		}else{
 			$('#'.concat('<portlet:namespace/>').concat('prev_month')).hide();
 		}
	}
	
	/**
	 * Set next month on click event on next_month element based on given arguments
	 *
	 * @Input currentYear - current year number value
	 * @Input currentMonth - current month number value
	 * @Input requiredVars - required vars function reference
	 */
	function <portlet:namespace/>setNextMonth(currentYear, currentMonth, requiredVars) {
		Object.prototype.hasOwnProperty.call(requiredVars, "name");
		if(currentMonth == 11) {
			$('#'.concat('<portlet:namespace/>').concat('next_month')).attr('onclick', '<portlet:namespace/>printCalendar(' + (currentYear + 1) + ', 0, ' + '<portlet:namespace/>getCalendarRequiredVars' + ')');
		} else {
			$('#'.concat('<portlet:namespace/>').concat('next_month')).attr('onclick', '<portlet:namespace/>printCalendar(' + currentYear + ', ' + (currentMonth + 1) + ', ' + '<portlet:namespace/>getCalendarRequiredVars' + ')');
		}
	}

	/**
	 * Function print calendar data today
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input requiredVars - required vars function reference
	 */
 	function <portlet:namespace/>printCalendarData(year, month, requiredVars) {
		var day = 1;
		var date = new Date(year, month, day);
		do {
			var eventJson = <portlet:namespace/>filterEventsByDay(year, month, day, <portlet:namespace/>currentMonthData);
			var element = $(requiredVars().displayDayTag.concat('#<portlet:namespace/>calendar_day_').concat(year).concat('_').concat(month).concat('_').concat(day));

			$(element).attr('data-numeroeventos', eventJson.length);
			if(eventJson.length > 0) {
				$(element).addClass('tiene-eventos');
				$(element).append("<span class='oculto'>Día con eventos</span>");
			}
			
			var date = new Date(year, month, ++day);
		} while (date.getMonth() == month);
	}

	/**
	 * Function get event filtered
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input day - desired day data
	 * @Input requiredVars - required vars function reference
	 */
	function <portlet:namespace/>showEvents(year, month, day, requiredVars) {
		var displayedDate = new Date(year, month, 1 + day);
		$("button").removeClass("focusDay");
		$('#<portlet:namespace/>calendar_day_'.concat(displayedDate.getFullYear()).concat("_").concat(displayedDate.getMonth()).concat("_").concat(displayedDate.getDate()-1)).addClass( "focusDay" );
		//var eventJson = <portlet:namespace/>filterEventsByDay(year, month, day, <portlet:namespace/>currentMonthData);
		var eventJson = <portlet:namespace/>filterEventsByWeek(year, month, day, <portlet:namespace/>currentMonthData);
		 <portlet:namespace/>fireIPCEvent(year, month, day, eventJson, {
			months: requiredVars().months,
			defaultLocal: requiredVars().defaultLocal,
			groupId: requiredVars().groupId,
			noEventsMessage: requiredVars().noEventsMessage,
			eventTitleFormat: requiredVars().eventTitleFormat
			}); 
	} 
			
	/**
	 * Function print calendar filtered
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input requiredVars - required vars function reference
	 */
	function <portlet:namespace/>printCalendar(year, month, requiredVars) {
		var date = new Date(year, month, 1);
		$('#<portlet:namespace/>month_name').text(requiredVars().months[month].concat(" - ").concat(year));
		<portlet:namespace/>setPrevMonth(year, month, requiredVars);
		<portlet:namespace/>setNextMonth(year, month, requiredVars);
		
		/* Remove prev data*/
		$('div.calendar-week-row').empty();
		
		/* Print first calendar row*/
		var row = $('#<portlet:namespace/>calendar_row-0');
		var it = ((date.getDay() - 1) % 7);
		var printedElements = 0;
		// Print if the day is Saturday
		if (date.getDay() == 0){
			for(x = 0; x < 6; x++) {
				var displayedDate = new Date(year, month, 1 - (((date.getDay() - 1) % 7) - x + 1));
				$('<'.concat(requiredVars().displayDayTag).concat('/>'),{
					text: String.fromCharCode(160),
					id: '<portlet:namespace/>calendar_day_'.concat("last_month_").concat(x),
					class: 'prev-month'
				}).prependTo(row);
				printedElements++;
			}
			it = 0;
		}
		for(y = it; y > 0; y--) {
			var displayedDate = new Date(year, month, 1 - (((date.getDay() - 1) % 7) - y + 1));
			$('<'.concat(requiredVars().displayDayTag).concat('/>'),{
				text: String.fromCharCode(160),
				id: '<portlet:namespace/>calendar_day_'.concat("last_month_").concat(y),
				class: 'prev-month'
			}).prependTo(row);
			printedElements++;
		}
		var today = new Date();
		var ddToday = today.getDate();
		var mmToday = today.getMonth();
		var yyyyToday  = today.getFullYear();
		/* Set remaing days */
		for(var iterator = 0; printedElements < 6 * 7; iterator++) {
			var displayedDate = new Date(year, month, 1 + iterator);
			var semanticSpan = "";
			if(printedElements % 7 == 0) {
				row = $('#<portlet:namespace/>calendar_row-'.concat(printedElements / 7));
			}
			var cssClass = "calendar-day";
			if(displayedDate.getMonth() != month) {
				cssClass = "next-month";
			}
			if(yyyyToday == year && mmToday == month && ddToday == 1 + iterator){
				cssClass+=" today";
				semanticSpan =" Día de hoy.";
			}
			var weekDay = displayedDate.getDay();
			if (weekDay == 0 || weekDay == 6){
				cssClass+=" weekEnd";
				semanticSpan +=" Fin de semana";
			}
			$('<'.concat(requiredVars().displayDayTag).concat('/>'),{
				text: (displayedDate.getMonth() != month) ? String.fromCharCode(160) :displayedDate.getDate(),
				id: (displayedDate.getMonth() != month) ? "<portlet:namespace/>calendar_day_next_month_"+iterator:"<portlet:namespace/>calendar_day_".concat(displayedDate.getFullYear()).concat("_").concat(displayedDate.getMonth()).concat("_").concat(displayedDate.getDate()),
				tabindex: 0,
				class: cssClass,
				title: (displayedDate.getMonth() != month) ? "Next month" : displayedDate.getDate()+ " - " + requiredVars().months[month].concat(" - ").concat(year),
				label: (displayedDate.getMonth() != month) ? "" : displayedDate.getDate()+ " - " + requiredVars().months[month].concat(" - ").concat(year),
				onclick: ((displayedDate.getMonth() != month) ? "" : "<portlet:namespace/>showEvents(".concat(year).concat(",").concat(month).concat(",").concat(displayedDate.getDate()).concat(", <portlet:namespace/>getCalendarRequiredVars)"))
			}).append("<span class='oculto'>"+semanticSpan+"</span>").appendTo(row);
			printedElements++;
		}
		
		/* Display current day if this.month = equals given month*/
		var currentDate = new Date();
		<portlet:namespace/>currentMonthData = jsonEvent;
		<portlet:namespace/>printCalendarData(year, month, requiredVars);
		var requireVars = <portlet:namespace/>getCalendarRequiredVars();
		<portlet:namespace/>printMenuFilter(year, month, jsonEvent,requireVars);
	}
	
	/**
	 * Function get title's event with fomatted
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input day - desired day data
	 * @Input requiredVars - required vars function reference
	 */
	function <portlet:namespace/>getEventDayTitle(year, month, day, eventJson, requiredVars, filter) {
 		if (filter == "day"){
 			return requiredVars.eventTitleFormat
			.replace("DD", day)
			.replace("MMMMM", requiredVars.months[month])
			.replace("MM", (month + 1))
			.replace("YYYY", year);
 		}else if (filter == "month"){
 			return requiredVars.months[month] + " del " + year;
 		}else if (filter == "weekCurrent"){
 			return "Eventos para esta semana";
 		}else if (filter == "week"){
 			return "Eventos para la semana seleccionada";
 		}else {
 			return "Próximos eventos";
 		}
	}
	/**
	 * Function print button's menu filter
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input jsonEvent - json Events
	 * @Input requiredVars - required vars function reference
	 */
	function <portlet:namespace/>printMenuFilter(year, month,jsonEvent, requireVar) {
		var dateToday = new Date();
		var ddToday = dateToday.getDate();
		var mmToday = dateToday.getMonth();
		var yyyyToday  = dateToday.getFullYear();
 		$('#<portlet:namespace/>filter_event_day').on("click", function(){
 			var filterDay = <portlet:namespace/>filterEventsByDay(yyyyToday, mmToday, ddToday, jsonEvent);
			<portlet:namespace/>showEventsList(yyyyToday, mmToday, ddToday, filterDay, requireVar, "day");
		});
		$('#<portlet:namespace/>filter_event_month').on("click", function(){ 
			var filterMonth = <portlet:namespace/>filterEventsByMonth(year, month, jsonEvent);
			<portlet:namespace/>showEventsList(year, month, "", filterMonth,requireVar, "month");
		});
		$('#<portlet:namespace/>filter_event_week').on("click", function(){ 
			var filterWeek = <portlet:namespace/>filterEventsByWeek(yyyyToday, mmToday, ddToday, jsonEvent);
			<portlet:namespace/>showEventsList(yyyyToday, mmToday, ddToday, filterWeek,requireVar, "weekCurrent");
		});
		$('#<portlet:namespace/>filter_event_all').on("click", function(){ 
			<portlet:namespace/>showEventsList(yyyyToday, mmToday, ddToday, jsonEvent,requireVar, "every");
		});
	}
	
	/**
	 * Function get event json data filter by day
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input day - desired day data
	 * @Input input - JSON Events
	 */
	function <portlet:namespace/>filterEventsByDay(year, month, day, input) {
		var dateFilter = new Date(year,month,day);
		var filterEvents = [];
		for (i = 0; i < input.length; i++) {
			Object.prototype.hasOwnProperty.call(input[i], "startDate");
			Object.prototype.hasOwnProperty.call(input[i], "endDate");
			Object.prototype.hasOwnProperty.call(input[i], "year");
			Object.prototype.hasOwnProperty.call(input[i], "month");
			Object.prototype.hasOwnProperty.call(input[i], "day");    
			var startDate = new Date(input[i]["startDate"]);
			var endDate = new Date(input[i]["endDate"]);
			if ((dateFilter.getTime() > startDate.getTime() && dateFilter.getTime() < endDate.getTime())
					|| (input[i]["year"] == year && input[i]["month"] == month && input[i]["day"] == day)){
				filterEvents.push(input[i]);
			}
		}
		return filterEvents;
	}
	/**
	 * Function get event json data filter by month
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input inut - list json Events
	 */
	function <portlet:namespace/>filterEventsByMonth(year, month, input) {
		var dateFilter = new Date(year,month,1);
		var filterEvents = [];
		for (i = 0; i < input.length; i++) {
			Object.prototype.hasOwnProperty.call(input[i], "startDate");
			Object.prototype.hasOwnProperty.call(input[i], "endDate");
			Object.prototype.hasOwnProperty.call(input[i], "year");
			Object.prototype.hasOwnProperty.call(input[i], "month"); 
			var startDate = new Date(input[i]["startDate"]);
			var endDate = new Date(input[i]["endDate"]);
			if ((dateFilter.getTime() > startDate.getTime() && dateFilter.getTime() < endDate.getTime())
				|| (input[i]["year"] == year && (input[i]["month"] == month))){
				filterEvents.push(input[i]);
			}
		}
		return filterEvents;
	}
	/**
	 * Function get event json data filter by week
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input day - desired day data
	 * @Input jsonEvent - json Events
	 * @Input requiredVars - required vars function reference
	 */
	function <portlet:namespace/>filterEventsByWeek(year, month, day, input) {
		var dateFilter = new Date(year,month,day);
		var dateWeek = getWeekOfYear(dateFilter);
		var filterEvents = [];
		for (i = 0; i < input.length; i++) {
			Object.prototype.hasOwnProperty.call(input[i], "startDate");
			Object.prototype.hasOwnProperty.call(input[i], "endDate");
			Object.prototype.hasOwnProperty.call(input[i], "year");
			Object.prototype.hasOwnProperty.call(input[i], "month"); 
			Object.prototype.hasOwnProperty.call(input[i], "week");
			var startDate = new Date(input[i]["startDate"]);
			var endDate = new Date(input[i]["endDate"]);
			if ((dateFilter.getTime() > startDate.getTime() && dateFilter.getTime() < endDate.getTime())
				|| (input[i]["year"] == year && input[i]["month"] == month && input[i]["week"].indexOf(dateWeek) == 0)){
				filterEvents.push(input[i]);
			}
		}
		return filterEvents;
	}
	/**
	 * Function get number week of year
	 *
	 * @Input date - desired year data
	 * @Return number week of year[0-51]
	 */
	function getWeekOfYear(date){
		var d = new Date(+date);
	    d.setHours(0,0,0);
	    d.setDate(d.getDate()+4-(d.getDay()||7));
	    return Math.ceil((((d-new Date(d.getFullYear(),0,1))/8.64e7)+1)/7);
	}
	/**
	 * Function show events in list with title and URL
	 *
	 * @Input year - desired year data
	 * @Input month - desired month data (0 based) [0-11]
	 * @Input day - desired day data
	 * @Input eventJson - json Events
	 * @Input requiredVars - required vars function reference
	 * @Input filter - required vars between these options: week, month, day or year
	 */
	function <portlet:namespace/>showEventsList(year, month, day, eventJson, requiredVars, filter) {
		var eventShowElement = $('#<portlet:namespace/>calendar_event_data');
		var maxShowElements = 100;
		if(maxShowElements > 0) {
			eventJson = eventJson.slice(0, maxShowElements)
		}
		/* Remove prev data*/
		$(eventShowElement).empty();
		$('#<portlet:namespace/>titulo-dia').text(<portlet:namespace/>getEventDayTitle(year, month, day, eventJson, requiredVars, filter));
		var divContainer = $('<div/>',{
			class: 'container'
		}).appendTo(eventShowElement);
		if(eventJson.length > 0) {
			var count = 0;
  			eventJson.forEach(function(event) {
  				if(count < 5){
  					var divRow = $('<div/>',{
  						class: 'row contenedor-evento'
  					}).appendTo(divContainer);
  					var divCol3 = $('<div/>',{
  						id: '<portlet:namespace/>event_item_'.concat(event.eventId),
  						class: 'col-4'
  					}).appendTo(divRow);
  					var divCol9 = $('<div/>',{
  						id: '<portlet:namespace/>event_item_'.concat(event.eventId),
  						class: 'col-8'
  					}).appendTo(divRow);
  					var textTime = "";
  					if (filter == "day"){
  						textTime = event.startHour.concat(' horas ' );
  					}else {
  						textTime = event.day + " " + event.monthName + " " + event.year;
  					}
  					var phora = $('<p/>',{
  						text: textTime,
  						id: '<portlet:namespace/>description_'.concat(event.eventId),
  						class: 'dateEvent'
  					}).appendTo(divCol3);
  					$('<a/>',{
  						href: event.url,
  						text: event.title,
  						id: '<portlet:namespace/>title_'.concat(event.eventId),
  						class: 'titulo-evento'
  					}).appendTo(divCol9);
  				}
  				if(count >= 5 && count <= eventJson.length){
  					var divRow = $('<div/>',{
  						class: 'row contenedor-evento oculto bloq-hidden'
  					}).appendTo(divContainer);
  					var divCol3 = $('<div/>',{
  						id: '<portlet:namespace/>event_item_'.concat(event.eventId),
  						class: 'col-4'
  					}).appendTo(divRow);
  					var divCol9 = $('<div/>',{
  						id: '<portlet:namespace/>event_item_'.concat(event.eventId),
  						class: 'col-8'
  					}).appendTo(divRow);
  					var textTime = "";
  					if (filter == "day"){
  						textTime = event.startHour.concat(' horas ' );
  					}else {
  						textTime = event.day + " " + event.monthName + " " + event.year;
  					}
  					var phora = $('<p/>',{
  						text: textTime,
  						id: '<portlet:namespace/>description_'.concat(event.eventId),
  						class: 'dateEvent'
  					}).appendTo(divCol3);
  					$('<a/>',{
  						href: event.url,
  						text: event.title,
  						id: '<portlet:namespace/>title_'.concat(event.eventId),
  						class: 'titulo-evento'
  					}).appendTo(divCol9);
  				}
  				count++;
  			});
  			if(eventJson.length > 5){
				var sectionBtn = $('<section/>',{
						id: 'btn-view-more'
					}).appendTo(eventShowElement);
					var divBtn = $('<div/>',{
						class: 'btn-module'
					}).appendTo(sectionBtn);
					var btn = $('<button/>',{
						id: 'btn-show-data',
						class: 'btn-view-more js-arrow-open collapsed',
						"aria-expanded": false,
						"aria-label":'Mostrar eventos',
						"data-label-closed":'Mostrar eventos',
						"data-label-opened":'Ocultar datos',
						text: 'Ver más eventos',
						onclick: 'viewMore()'
					}).appendTo(divBtn);
  			} 						
		} else {
			if (requiredVars != ""){
				$('<p/>',{
					text: requiredVars.noEventsMessage,
					id: '<portlet:namespace/>no-events-on-day',
					class: 'no-events-on-day'
				}).appendTo(eventShowElement);
			}
		}
	}
</script>