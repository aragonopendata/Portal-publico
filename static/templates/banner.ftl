<!--
Template Name: Banner
Template Description: Banner
Template Key: BANNER
Structure Key: BANNER
Cacheable: true
Small Image: false
-->
<div class="container u-container-mobile-0">
	<#if style.getData()?has_content && style.getData() == "question">
		<div class="banner-icon banner-icon-question">
	<#elseif style.getData()?has_content && style.getData() == "folder">
		<div class="banner-icon banner-icon-folder">
	<#elseif style.getData()?has_content && style.getData() == "close">
		<div class="banner-icon banner-icon-question" style="background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAuCAYAAABqK0pRAAAACXBIWXMAAC4jAAAuIwF4pT92AAADsElEQVRoge1ZPW8TQRB9F4U6ERVdEok+TkVpu0CiOAGR6O3ASXRg/wCURPwATEERdFLsXxAjTjQ0sZSGiril8kkUCCERdxRIhwbewHi5JI59B+fIT4r2Mvv5dmZnZ8dekiTIA1HgLQOoA7jL4QcA2n6YHOYxXy5ESEIWvJ5SveWHSTvrOReyHpBonEJCsB8FXinrCTMnQm00jGgLwAaAnpHVs543D420ACzxuyNm5IfJMeWKetZayZRIFHhi+zUjqpjvVfMtRA+zJJPZYU8hoYgBiEbupNQNAZT8MBlMO38mGkkhYc/DikOiTwKgZro8V1NhaiIpJMS9ikntpjTv8V6pGDLrNLOpyExlWlHgiXd6ZkS7fpjsmHpZnJ6DEx56rSvxrlHHIJqq+GFyMslaJiYSBZ640H0jEg91IbeaJZmJTCsLEgJqyPab2MwuTCQrEgo/TLq8NBUTkbkQkaxJKBh7uWRa53QbwdhE8iKhSCFTo0ccC2MRiQKv4pDoO/HUfydzLhF6lq4RTeUmz8OkZH66X/MIavAmPgtCJBcSDkrGLZ+Gjpwl8X5KZDAGgSKjukD7n2USgvqiE2pX83pT54Eo8DQsqeX11P3nmBMpGuZEiobLT4T3S2EQBd7qWWvyXj+APE23+X+V90qD4UHMOOsDgBemn7y3HwLYc8KI53zxHThtG5omNbFb1w+TBtIj65h9NDEh7cumrs0w/+tpGrlGUkuMqeTGfwzgUcomVA3ZPmXS9paZsM82LfNQaum4ssuU6XNAMyxSf8B6JTFk8mKFaxwJJF0i91g2/TCRnVvjgr9Q3mF2RCb+RFmbbTUF9E3l1G6sg9M0yn+mww5GIVpYNhmY+4ZEhdmZDdaN5MlcItfxK5RusRwwZPnO+hp3Q0xHd3iboUKZZDQy3qbqV0j2xCy8o+MZrVhomHSb5bFmYFj23A6ph51pHj1gsvuLZgGyW5tmwbHZdRvexyZ3NXC0YfNgrlZgTO0zy5KmV1mW3Q7uYX8C4Cm/e6ZD3/mZYEjbrZGYfL9n3R4dwS5TpXrw3wK4mbZxAN4BuMF5Vo0D2aTjUfM6Nmt6Zc3L1cgRFzBkh5ie6KXTTia6ooNT3fqq+6hEmSFRM7rK8db8MPHkj2NL2zdss86xh8xYdpmZbFLbSqLj/jTxl/stchgvZuVkK39nF2fqZrckXMxjraJhTqRouDREFp3buFG08H1MxHKPLNtweEbRXGAw17RR6oxBbvn2yE9vM2hWEp0PAOAH5iSBagzJ3PIAAAAASUVORK5CYII=');">
	<#else>
		<div class="banner-icon banner-icon-telephone">
	</#if>
			${text.getData()}
		</div>
</div>