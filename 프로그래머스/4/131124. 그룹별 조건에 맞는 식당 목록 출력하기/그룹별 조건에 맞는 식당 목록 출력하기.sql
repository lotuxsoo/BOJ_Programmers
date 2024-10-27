select M.MEMBER_NAME, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE,"%Y-%m-%d") as REVIEW_DATE
from MEMBER_PROFILE M inner join REST_REVIEW R
on M.MEMBER_ID = R.MEMBER_ID
where R.MEMBER_ID = (
    select MEMBER_ID from REST_REVIEW
    group by MEMBER_ID
    order by COUNT(*) desc
    limit 1
)
order by R.REVIEW_DATE asc, R.REVIEW_TEXT asc;