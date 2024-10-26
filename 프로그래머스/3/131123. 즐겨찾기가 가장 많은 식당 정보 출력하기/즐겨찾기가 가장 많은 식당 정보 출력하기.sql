select FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO as R
where FAVORITES = (
    select MAX(FAVORITES)
    from REST_INFO
    where FOOD_TYPE = R.FOOD_TYPE
)
order by FOOD_TYPE desc;