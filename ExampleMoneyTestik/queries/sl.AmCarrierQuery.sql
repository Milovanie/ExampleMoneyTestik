SELECT 
  t1.*
  FROM 
  ams.Carrier t1 JOIN ams.businesspartner t2 ON t1.businesspartnerid = t2.uniqueid
 WHERE 
  t2.searchname LIKE E'#searchname' AND
  t2.businesspartnernr LIKE E'#businesspartnernr' AND
  t1.active = #active
ORDER BY
  t2.searchname ASC,
  t2.businesspartnernr ASC