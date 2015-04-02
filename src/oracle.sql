SELECT T.table_name
  FROM User_Tab_Comments T
 WHERE T.table_type = 'TABLE'
   and t.table_name not like '%$%'
   and t.table_name not like 'TMP_%'
   and t.table_name not like 'TEMP_%'
   and t.table_name not like 'SYS_%'
   and t.table_name not like 'FREIGHTBILLING_%'
   and t.table_name not like 'TEST%'
   and t.table_name not in ('HUB_PROCESS_MATRIX0314',
                            'HUB_PROCESS_MATRIX_140319_BAK',
                            'HUB_PROCESS_MATRIX_140509',
                            'HUB_PROCESS_MATRIX_20140318',
                            'INVENTORY0318',
                            'MST_DA_MAS_LOC0314',
                            'MST_DA_MAS_LOC2',
                            'MST_DA_MAS_LOC_140321',
                            'MST_DA_MAS_LOC_140509',
                            'MST_DELIVERY_AREA0314',
                            'MST_DELIVERY_AREA_0321',
                            'MST_DELIVERY_AREA_140509',
                            'MST_RESERVE_RULE2',
                            'MST_RESERVE_GROUP_DETAIL2',
                            'PART_BIN_0119',
                            'DAMAGE_APP_INFO_TEMP1',
                            'DAMAGE_FEEDBACK_HIS',
                            'FTP_ASN_ERROR1',
                            'INVENTORY_DETAIL_DAILY_CUTOFF',
                            'INVENTORY_DETAIL_DEDUCTIBLE',
                            'INVENTORY_DETAIL_TEMP_CZ',
                            'TABLE_A')