/**
 * 利用数据库的表信息，查询出相应的表名，进行批量生成
 * oracle 可以通过SELECT T.table_name FROM User_Tab_Comments T WHERE T.table_type = 'TABLE' and t.table_name not like 'TMP_%' and 。。。
 * mysql 可以通过SELECT TABLE_NAME  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'XXX' AND TABLE_TYPE = 'BASE TABLE' and ...
 */
/**SELECT T.table_name
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
    and 1=1
*/
/**
SELECT TABLE_NAME 
  FROM INFORMATION_SCHEMA.TABLES
 WHERE TABLE_SCHEMA = 'qilin_dev'
   AND TABLE_TYPE = 'BASE TABLE'
   and table_name not in ('asn_box_test001',
                          'eta_tmp',
                          'hub_process_matrix_0330',
                          'hub_process_matrix_d001_1101',
                          'hub_process_matrix_d005_1101',
                          'hub_process_matrix_history',
                          'lsp_da_daily_info_1228_2',
                          'lsp_da_daily_info_1228_3',
                          'lsp_da_daily_info_1228_4',
                          'message_fallout_0917',
                          'message_transactions_un1005',
                          'mst_da_mas_loc_0330',
                          'mst_delivery_area_0330',
                          'mst_division_0330',
                          'mst_division_140509',
                          'mst_division_dba_140423',
                          'mst_lsp_da_amount_0330',
                          'mst_lsp_da_amount_140509',
                          'mst_lsp_da_share_0330',
                          'mst_lsp_da_share_140509',
                          'mst_lsp_pickup_schedule_0515',
                          'mst_order_eta_leadtime_bak',
                          'mst_return_masloc_0330',
                          'mst_return_masloc_140509',
                          'mst_trans_capacity_140319_bak',
                          'mst_trans_capacity_temp_modify',
                          'tbl_diff2',
                          'tbl_diff3',
                          'tbl_diff5',
                          'tbl_diff_dml',
                          'hub_process_matrix_140319_bak',
                          'hub_process_matrix_140509',
                          'hub_process_matrix_20140318',
                          'mst_da_mas_loc_140509',
                          'mst_da_mas_loc_bk',
                          'mst_da_mas_loc_bk',
                          'mst_da_mas_loc_new',
                          'mst_delivery_area_140509',
                          'mst_rack_0706_db01',
                          'part_bin_0119',
                          'temp_1011',
                          'dbexpert_plan1')
   and table_name not like '%$%'
   and table_name not like 'TMP_%'
   and table_name not like 'tmp_%'
   and table_name not like 'TEMP_%'
   and table_name not like 'temp_%'
   and table_name not like 'SYS_%'
   and table_name not like 'sys_%'
   and table_name not like 'FREIGHTBILLING_%'
   and table_name not like 'freightbilling_%'
   and table_name not like 'TEST%'
   and table_name not like 'test%'
   and 1=1
*/

SELECT TABLE_NAME 
  FROM INFORMATION_SCHEMA.TABLES
 WHERE TABLE_SCHEMA = 'qilin_dev'
   AND TABLE_TYPE = 'BASE TABLE'
   and table_name not in ('asn_box_test001',
                          'eta_tmp',
                          'hub_process_matrix_0330',
                          'hub_process_matrix_d001_1101',
                          'hub_process_matrix_d005_1101',
                          'hub_process_matrix_history',
                          'lsp_da_daily_info_1228_2',
                          'lsp_da_daily_info_1228_3',
                          'lsp_da_daily_info_1228_4',
                          'message_fallout_0917',
                          'message_transactions_un1005',
                          'mst_da_mas_loc_0330',
                          'mst_delivery_area_0330',
                          'mst_division_0330',
                          'mst_division_140509',
                          'mst_division_dba_140423',
                          'mst_lsp_da_amount_0330',
                          'mst_lsp_da_amount_140509',
                          'mst_lsp_da_share_0330',
                          'mst_lsp_da_share_140509',
                          'mst_lsp_pickup_schedule_0515',
                          'mst_order_eta_leadtime_bak',
                          'mst_return_masloc_0330',
                          'mst_return_masloc_140509',
                          'mst_trans_capacity_140319_bak',
                          'mst_trans_capacity_temp_modify',
                          'tbl_diff2',
                          'tbl_diff3',
                          'tbl_diff5',
                          'tbl_diff_dml',
                          'hub_process_matrix_140319_bak',
                          'hub_process_matrix_140509',
                          'hub_process_matrix_20140318',
                          'mst_da_mas_loc_140509',
                          'mst_da_mas_loc_bk',
                          'mst_da_mas_loc_bk',
                          'mst_da_mas_loc_new',
                          'mst_delivery_area_140509',
                          'mst_rack_0706_db01',
                          'part_bin_0119',
                          'temp_1011',
                          'dbexpert_plan1')
   and table_name not like '%$%'
   and table_name not like 'TMP_%'
   and table_name not like 'tmp_%'
   and table_name not like 'TEMP_%'
   and table_name not like 'temp_%'
   and table_name not like 'SYS_%'
   and table_name not like 'sys_%'
   and table_name not like 'FREIGHTBILLING_%'
   and table_name not like 'freightbilling_%'
   and table_name not like 'TEST%'
   and table_name not like 'test%'