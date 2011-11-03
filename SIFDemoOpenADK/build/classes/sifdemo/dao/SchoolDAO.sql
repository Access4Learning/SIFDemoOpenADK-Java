<?xml version="1.0" encoding="UTF-8" ?>
<statements datasource="sifdemo">

    <statement name="getAllSchools">
      <![CDATA[
   		select  LOCAL_ID,
				SCHOOL_NAME,
				DISTRICT_ID,
				DISTRICT_NAME,
				SCHOOL_TYPE,
				GOVERNMENT_SCHOOL,
				RELIGIOUS_AFFILIATION
		from SCHOOL
      ]]>
   </statement>
   
</statements>