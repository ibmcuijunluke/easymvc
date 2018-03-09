package com.easymvc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ComplementaryUtil {

	private static Map<String, Integer> getAppListCount(String result) {
		List<Integer> SY_List = new ArrayList<Integer>();
		List<Integer> DJ_List = new ArrayList<Integer>();
		List<Integer> ZYY_List = new ArrayList<Integer>();
		List<Integer> DJHongBao_List = new ArrayList<Integer>();
		List<Integer> FUNC_List = new ArrayList<Integer>();
		List<Integer> HouBu_List = new ArrayList<Integer>();
		List<Integer> CPM_List = new ArrayList<Integer>();
		List<Integer> WIFI_List = new ArrayList<Integer>();
		List<Integer> exceptionList = new ArrayList<Integer>();
		Map<String, Integer> appMap=new HashMap<String, Integer>();
		if (result.length() > 0 && result!=null) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray style_array = jsonObject.getJSONArray("style");
			for (int i = 0; i < style_array.size(); i++) {
				JSONObject style_object = style_array.getJSONObject(i);
				JSONArray app_list_array = style_object.getJSONArray("app_list");
				for (int j = 0; j < app_list_array.size(); j++) {
					JSONObject app_list_object = app_list_array.getJSONObject(j);
					int r_id = app_list_object.getInt("r_id");
					if (r_id == 1) {
						SY_List.add(r_id);
						appMap.put("手游", SY_List.size());
					} else if (r_id == 2) {
						DJ_List.add(r_id);
						appMap.put("点睛", DJ_List.size());
					} else if (r_id == 3) {
						ZYY_List.add(r_id);
						appMap.put("手游(自运营)", ZYY_List.size());
					} else if (r_id == 4) {
						DJHongBao_List.add(r_id);
						appMap.put("点睛(红包)", DJHongBao_List.size());
					} else if (r_id == 5) {
						FUNC_List.add(r_id);
						appMap.put("功能推荐", FUNC_List.size());
					} else if (r_id == 9) {
						HouBu_List.add(r_id);
						appMap.put("候补", HouBu_List.size());
					} else if (r_id == 10) {
						CPM_List.add(r_id);
						appMap.put("CPM", CPM_List.size());
					} else if (r_id == 11) {
						WIFI_List.add(r_id);
						appMap.put("WIFI(点睛)", WIFI_List.size());
					}else{
						appMap.put("未知数据源", exceptionList.size());
					}
				}
			}
		}
		return appMap;
	}

	private static Map<String, Integer> getNewsListCount(String result) {
		List<Integer> news_list = new ArrayList<Integer>();
		List<Integer> GSC_list = new ArrayList<Integer>();
		List<Integer> SOUHOT_list = new ArrayList<Integer>();
		List<Integer> SOULIKE_list = new ArrayList<Integer>();
		List<Integer> CPMXXL_list = new ArrayList<Integer>();
		List<Integer> exceptionList = new ArrayList<Integer>();
		Map<String, Integer> newsListMap = new HashMap<String, Integer>();
		if (result.length() > 0 && result!=null) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray news_style_array = jsonObject.getJSONArray("news_style");
			for (int i = 0; i < news_style_array.size(); i++) {
				JSONObject news_style_object = news_style_array.getJSONObject(i);
				JSONArray news_list_array = news_style_object.getJSONArray("news_list");
				for (int j = 0; j < news_list_array.size(); j++) {
					int r_id = news_list_array.getJSONObject(j).getInt("r_id");
					if (r_id == 101) {
						news_list.add(r_id);
						newsListMap.put("信息流", news_list.size());
					} else if (r_id == 102) {
						GSC_list.add(r_id);
						newsListMap.put("高商词", GSC_list.size());
					} else if (r_id == 103) {
						SOUHOT_list.add(r_id);
						newsListMap.put("搜索热词", SOUHOT_list.size());
					} else if (r_id == 104) {
						SOULIKE_list.add(r_id);
						newsListMap.put("猜你喜欢", SOULIKE_list.size());
					} else if (r_id == 105) {
						CPMXXL_list.add(r_id);
						newsListMap.put("CPM_信息流", CPMXXL_list.size());
					}else{
						newsListMap.put("未知数据源", exceptionList.size());
					}
				}
			}
		}
		return newsListMap;
	}
	
	private static Map<String, Integer> getMVListCount(String result) {
		List<Integer> MV_list = new ArrayList<Integer>();
		List<Integer> exceptionList = new ArrayList<Integer>();
		Map<String, Integer> MVListMap = new HashMap<String, Integer>();
		if (result.length() > 0 && result!=null) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray mv_style_array = jsonObject.getJSONArray("mv_style");
			for (int i = 0; i < mv_style_array.size(); i++) {
				JSONArray mv_list_array = mv_style_array.getJSONObject(i).getJSONArray("mv_list");
				for (int j = 0; j < mv_list_array.size(); j++) {
					JSONObject mvListObject = mv_list_array.getJSONObject(j);
					int r_id = mvListObject.getInt("r_id");
					//int interaction_type=mvListObject.getInt("interaction_type");
					if (r_id == 201) {
						MV_list.add(MV_list.size());
						MVListMap.put("MV", MV_list.size());
					}
					/*if (r_id == 201 && interaction_type==2) {
						MV_list.add(MV_list.size());
						MVListMap.put("MVH5", MV_list.size());
					}else if (r_id == 201 && interaction_type==3) {
						MV_list.add(MV_list.size());
						MVListMap.put("MVBASE", MV_list.size());
					}*//*
					if (r_id == 201) {
						MV_list.add(MV_list.size());
						MVListMap.put("MV", MV_list.size());
					}*/else{
						exceptionList.add(r_id);
						MVListMap.put("未知数据源", exceptionList.size());
					}
				}
			}
		}
		return MVListMap;
	}
	
	private static Map<String, Integer> getActivityListCount(String result) {
		List<Integer> activityList = new ArrayList<Integer>();
		List<Integer> CPM_KPList = new ArrayList<Integer>();
		List<Integer> exceptionList = new ArrayList<Integer>();
		Map<String, Integer> activityListMap = new HashMap<String, Integer>();
		if (result.length() > 0 && result!=null) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray activity_style_array = jsonObject.getJSONArray("activity_style");
			for (int i = 0; i < activity_style_array.size(); i++) {
				JSONObject activity_list_object = activity_style_array.getJSONObject(i);
				JSONArray activity_list_array = activity_list_object.getJSONArray("activity_list");
				for (int j = 0; j < activity_list_array.size(); j++) {
					JSONObject activityListObject = activity_list_array.getJSONObject(j);
					int r_id = activityListObject.getInt("r_id");
					String activity_id = activityListObject.getString("activity_id");
					if(r_id==301){
						activityList.add(r_id);
						activityListMap.put("开屏(KP)", activityList.size());
					}else if(r_id==302){
						CPM_KPList.add(r_id);
						activityListMap.put("CPM开屏"+activity_id, CPM_KPList.size());
					}else{
						exceptionList.add(r_id);
						activityListMap.put("未知数据源", exceptionList.size());
					}
				}
			}
		}
		return activityListMap;
	}
	
	private static Map<String, Integer> getTongChengStyleCount(String result) {
		List<Integer> tongcheng_list = new ArrayList<Integer>();
		Map<String, Integer> tongchengListMap = new HashMap<String, Integer>();
		if (result.length() > 0 && result!=null) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray activity_style_array = jsonObject.getJSONArray("tongcheng_style");
			for (int i = 0; i < activity_style_array.size(); i++) {
				JSONObject activity_list_object = activity_style_array.getJSONObject(i);
				JSONArray activity_list_array = activity_list_object.getJSONArray("tongcheng_list");
				for (int j = 0; j < activity_list_array.size(); j++) {
					JSONObject activityListObject = activity_list_array.getJSONObject(j);
					int r_id = activityListObject.getInt("r_id");
					if(r_id==402){
						tongcheng_list.add(r_id);
						tongchengListMap.put("同城广告", tongcheng_list.size());
					}else{
						tongcheng_list.add(r_id);
						tongchengListMap.put("同城广告异常", tongcheng_list.size());
					}
				}
			}
		}
		return tongchengListMap;
	}
	
	private static Map<String, Integer> getInmobiStyleCount(String result) {
		List<Integer> inmobi_list = new ArrayList<Integer>();
		Map<String, Integer> inmobiListMap = new HashMap<String, Integer>();
		if (result.length() > 0 && result!=null) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray activity_style_array = jsonObject.getJSONArray("inmobi_style");
			for (int i = 0; i < activity_style_array.size(); i++) {
				JSONObject activity_list_object = activity_style_array.getJSONObject(i);
				JSONArray activity_list_array = activity_list_object.getJSONArray("inmobi_list");
				for (int j = 0; j < activity_list_array.size(); j++) {
					JSONObject activityListObject = activity_list_array.getJSONObject(j);
					int r_id = activityListObject.getInt("r_id");
					if(r_id==405){
						inmobi_list.add(r_id);
						inmobiListMap.put("Inmobi广告", inmobi_list.size());
					}else if(r_id==410){
						inmobi_list.add(r_id);
						System.out.println(activityListObject);
						inmobiListMap.put("Inmobi视频广告", inmobi_list.size());
					}else{
						inmobi_list.add(r_id);
						inmobiListMap.put("Inmobi广告异常", inmobi_list.size());
					}
				}
			}
		}
		return inmobiListMap;
	}
	
	
	private static Map<String, Integer> dataSourceIDMap() {
		Map<String, Integer> appCount = new HashMap<String, Integer>();
		appCount.put("手游", 0);
		appCount.put("点睛", 0);
		appCount.put("手游(自运营)", 0);
		appCount.put("点睛(红包)", 0);
		appCount.put("功能推荐", 0);
		appCount.put("候补", 0);
		appCount.put("CPM", 0);
		appCount.put("WIFI(点睛)", 0);
		appCount.put("信息流", 0);
		appCount.put("高商词", 0);
		appCount.put("搜索热词", 0);
		appCount.put("猜你喜欢", 0);
		appCount.put("CPM_信息流", 0);
		appCount.put("MV", 0);
		appCount.put("开屏(KP)", 0);
		appCount.put("CPM开屏", 0);
		appCount.put("头条", 0);
		appCount.put("同城广告", 0);
		appCount.put("Inmobi视频广告", 0);
		
		return appCount;
	}

	public static Map<String, Integer> dataSourceCountMap(String result) {
		Map<String, Integer> dataSourceMap = dataSourceIDMap();
		Map<String, Integer> appMap = getAppListCount(result);
		for (Entry<String, Integer> appEntry : appMap.entrySet()) {
			dataSourceMap.put(appEntry.getKey(), appEntry.getValue());
		}
		Map<String, Integer> newsMap = getNewsListCount(result);
		for (Entry<String, Integer> newsEntry : newsMap.entrySet()) {
			dataSourceMap.put(newsEntry.getKey(), newsEntry.getValue());
		}
		Map<String, Integer> mvMap = getMVListCount(result);
		for (Entry<String, Integer> mvEntry : mvMap.entrySet()) {
			dataSourceMap.put(mvEntry.getKey(), mvEntry.getValue());
		}
		Map<String, Integer> activityMap = getActivityListCount(result);
		for (Entry<String, Integer> activityEntry : activityMap.entrySet()) {
			dataSourceMap.put(activityEntry.getKey(), activityEntry.getValue());
		}
		Map<String, Integer> tongChengMap = getTongChengStyleCount(result);
		for (Entry<String, Integer> tongChengMapEntry : tongChengMap.entrySet()) {
			dataSourceMap.put(tongChengMapEntry.getKey(), tongChengMapEntry.getValue());
		}
		Map<String, Integer> inmobiStyleMap = getInmobiStyleCount(result);
		for (Entry<String, Integer> inmobiStyleMapEntry : inmobiStyleMap.entrySet()) {
			dataSourceMap.put(inmobiStyleMapEntry.getKey(), inmobiStyleMapEntry.getValue());
		}
		
		return dataSourceMap;
	}
	
	
	/*
	public static int getRuleIdByLogResult(String url, String debug_key) {
		int rule_id = 0;
		try {
			String requestUrl = url + debug_key;
			String logResult = HttpClientUtils.sendPostRequest(requestUrl);
			if (logResult != null && logResult.length() > 0) {
				JSONObject jsonObject = JSONObject.fromObject(logResult)
						.getJSONObject("rule_info");
				rule_id = jsonObject.getInt("rule_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rule_id;
	}

	public static String getAppConutByLogResult(String url, String debug_key) {
		String appConut = "";
		try {
			String requestUrl = url + debug_key;
			String logResult = HttpClientUtils.sendPostRequest(requestUrl);
			if (logResult != null && logResult.length() > 0) {
				appConut = JSONObject.fromObject(logResult)
						.getJSONObject("response_ad_count").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appConut;
	}

	public static String getLogInfoResult(String url, String debug_key) {
		String logResult = "";
		try {
			String requestUrl = url + debug_key;
			logResult = HttpClientUtils.sendPostRequest(requestUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logResult;
	}



	public static List<String> getXXLDescription(String result) {
		List<String> news_style_image_detail_list = new ArrayList<String>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray news_style_array = jsonObject.getJSONArray("news_style");
			for (int i = 0; i < news_style_array.size(); i++) {
				JSONObject news_style_object = news_style_array
						.getJSONObject(i);
				JSONArray news_list_array = news_style_object
						.getJSONArray("news_list");
				for (int j = 0; j < news_list_array.size(); j++) {
					JSONArray image_detail_array = news_list_array
							.getJSONObject(j).getJSONArray("image_detail");
					news_style_image_detail_list.add("image_detail:"
							+ image_detail_array.toString());
				}
			}
		}
		return news_style_image_detail_list;
	}

	public static Integer getActivityStyleCount(String result) {
		List<Integer> activity_style_list = new ArrayList<Integer>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray activity_style_array = jsonObject
					.getJSONArray("activity_style");
			for (int i = 0; i < activity_style_array.size(); i++) {
				JSONObject activity_style_object = activity_style_array.getJSONObject(i);
				int seq_id = activity_style_object.getInt("seq_id");
				activity_style_list.add(seq_id);
			}
		}
		int activity_style_count = activity_style_list.size();
		return activity_style_count;
	}

	public static Integer getMVStyleCount(String result) {
		List<Integer> mv_style_list = new ArrayList<Integer>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray mv_style_array = jsonObject.getJSONArray("mv_style");
			for (int i = 0; i < mv_style_array.size(); i++) {
				JSONObject mv_style_object = mv_style_array.getJSONObject(i);
				int seq_id = mv_style_object.getInt("seq_id");
				mv_style_list.add(seq_id);
			}
		}
		int mv_style_count = mv_style_list.size();
		return mv_style_count;
	}

	

	public static String getActuallyResult(String result) {
		List<String> reslutList = new ArrayList<String>();
		List<String> news_style = news_style(result);
		List<String> activity_style = activity_style(result);
		List<String> mv_style = mv_style(result);
		if (news_style != null) {
			for (int i = 0; i < news_style.size(); i++) {
				reslutList.add(news_style.get(i));
			}
		}
		if (activity_style != null) {
			for (int i = 0; i < activity_style.size(); i++) {
				reslutList.add(activity_style.get(i));
			}
		}
		if (mv_style != null) {
			for (int i = 0; i < mv_style.size(); i++) {
				reslutList.add(mv_style.get(i));
			}
		}
		List<List<String>> styleList = style(result);
		if (styleList != null) {
			for (int i = 0; i < styleList.size(); i++) {
				List<String> styleResultList = styleList.get(i);
				for (int j = 0; j < styleResultList.size(); j++) {
					reslutList.add(styleResultList.get(j));
				}
			}
		}

		List<String> compareStrList = new ArrayList<String>();
		for (int i = 0; i < reslutList.size(); i++) {
			compareStrList.add(reslutList.get(i));
		}
		List<SortIntToStringUtils> list = SortIntToStringUtils
				.compareStr(compareStrList);
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + String.valueOf(list.get(i)) + "\n";
		}
		return str;
	}



	public static List<String> mv_style(String result) {
		String mv_list_result = "";
		List<String> list = new ArrayList<String>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray mv_style_array = jsonObject.getJSONArray("mv_style");
			String picture = "MV广告";
			for (int i = 0; i < mv_style_array.size(); i++) {
				JSONObject mv_list_object = mv_style_array.getJSONObject(i);
				int seq_id = mv_list_object.getInt("seq_id");
				int type = mv_list_object.getInt("type");
				JSONArray mv_list_array = mv_list_object
						.getJSONArray("mv_list");
				for (int j = 0; j < mv_list_array.size(); j++) {
					JSONObject mvListObject = mv_list_array.getJSONObject(j);
					int mvListObject_seq_id = mvListObject.getInt("seq_id");
					mv_list_result = "位置" + seq_id + "出" + type + "模版,"
							+ mv_list_array.size() + "个" + picture + ",广告格"
							+ mvListObject_seq_id + "出MV;";
					list.add(mv_list_result);
				}
			}
		}
		return list;
	}

	public static List<String> news_style(String result) {
		String news_style_result = "";
		List<String> list = new ArrayList<String>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray news_style_array = jsonObject.getJSONArray("news_style");
			String picture = "信息流广告";
			for (int i = 0; i < news_style_array.size(); i++) {
				JSONObject news_style_object = news_style_array
						.getJSONObject(i);
				int seq_id = news_style_object.getInt("seq_id");
				int type = news_style_object.getInt("type");
				JSONArray news_list_array = news_style_object
						.getJSONArray("news_list");
				for (int j = 0; j < news_list_array.size(); j++) {
					JSONObject news_list_object = news_list_array
							.getJSONObject(j);
					int news_list_seq_id = news_list_object.getInt("seq_id");
					news_style_result = "位置" + seq_id + "出" + type + "模版,"
							+ news_list_array.size() + "个" + picture + ",广告格"
							+ news_list_seq_id + "出XXL;";
					list.add(news_style_result);
				}
			}
		}
		return list;
	}

	public static List<List<String>> style(String result) {
		List<List<String>> styleResultList = new ArrayList<List<String>>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray style_array = jsonObject.getJSONArray("style");
			for (int i = 0; i < style_array.size(); i++) {
				JSONObject style_object = style_array.getJSONObject(i);
				int seq_id = style_object.getInt("seq_id");
				int type = style_object.getInt("type");
				JSONArray app_list_array = style_object
						.getJSONArray("app_list");
				String dataSource = "", templateName = "";
				String adsPicture = "";
				int app_list_seq_id = 0;
				List<String> list = new ArrayList<String>();
				for (int j = 0; j < app_list_array.size(); j++) {
					JSONObject app_list_object = app_list_array
							.getJSONObject(j);
					int r_id = app_list_object.getInt("r_id");
					app_list_seq_id = app_list_object.getInt("seq_id");
					String large_image_url = app_list_object
							.getString("large_image_url");
					String image_url = app_list_object.getString("image_url");
					String banner = app_list_object.getString("banner");
					dataSource = getDataSource(r_id);
					adsPicture = getAdsPicture(dataSource, large_image_url,
							image_url, banner);
					templateName = getdataSource(adsPicture);
					list.add(app_list_seq_id + ":" + adsPicture);
				}
				int count = app_list_array.size();
				List<String> style_result_list = getStyleResultList(list,
						seq_id, count, templateName, app_list_seq_id, type);
				styleResultList.add(style_result_list);
			}
		}
		return styleResultList;
	}

	public static List<String> getStyleResultList(List<String> list,
			int seq_id, int count, String dataSource, int app_list_seq_id,
			int type) {
		List<String> style_result_list = new ArrayList<String>();
		List<Integer> DJ_seq_id_list = new ArrayList<Integer>();
		List<Integer> ZYY_seq_id_list = new ArrayList<Integer>();
		List<Integer> KP_seq_id_list = new ArrayList<Integer>();
		List<Integer> FUNC_seq_id_list = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			String[] array = list.get(i).split(":");
			String value = array[1];
			if ("DJ".equals(value)) {
				DJ_seq_id_list.add(Integer.parseInt(array[0]));
			} else if ("ZYY".equals(value)) {
				ZYY_seq_id_list.add(Integer.parseInt(array[0]));
			} else if ("KP".equals(value)) {
				KP_seq_id_list.add(Integer.parseInt(array[0]));
			} else if ("FUNC".equals(value)) {
				FUNC_seq_id_list.add(Integer.parseInt(array[0]));
			}
		}
		String FUNC = getStyleResult(FUNC_seq_id_list, seq_id, type, count,
				dataSource, "FUNC");
		String KP = getStyleResult(KP_seq_id_list, seq_id, type, count,
				dataSource, "KP");
		String ZYY = getStyleResult(ZYY_seq_id_list, seq_id, type, count,
				dataSource, "ZYY");
		String DJ = getStyleResult(DJ_seq_id_list, seq_id, type, count,
				dataSource, "DJ");
		if (FUNC.length() > 0) {
			style_result_list.add(FUNC);
		}
		if (KP.length() > 0) {
			style_result_list.add(KP);
		}
		if (ZYY.length() > 0) {
			style_result_list.add(ZYY);
		}
		if (DJ.length() > 0) {
			style_result_list.add(DJ);
		}
		return style_result_list;
	}

	public static String getStyleResult(List<Integer> dataSource_seq_id_list,
			int seq_id, int type, int count, String dataSource,
			String adsPicture) {
		String style_result = "";
		if (dataSource_seq_id_list.size() > 0) {
			String numList = "";
			for (int j = 0; j < dataSource_seq_id_list.size(); j++) {
				numList = numList + dataSource_seq_id_list.get(j) + ",";
			}
			numList = numList.substring(0, numList.length() - 1);
			style_result = "位置" + seq_id + "出" + type + "模版," + count + "个"
					+ dataSource + ",广告格" + numList + "出" + adsPicture + ";";
		}
		return style_result;
	}

	public static String getDataSource(int r_id) {
		String dataSource = "";
		if (r_id == 1) {
			dataSource = "Game";// 已废弃
		} else if (r_id == 2) {
			dataSource = "DianJing";
		} else if (r_id == 3) {
			dataSource = "ZiYunYing";
		} else if (r_id == 4) {
			dataSource = "DianJingHongBao";// 已废弃
		} else if (r_id == 5) {
			dataSource = "功能广告";
		} else if (r_id == 6) {
			dataSource = "XinXiLiu";
		} else if (r_id == 7) {
			dataSource = "MediaV";
		} else if (r_id == 8) {
			dataSource = "KP";
		} else if (r_id == 9) {
			dataSource = "DianJing";
		}
		return dataSource;
	}

	public static String getAdsPicture(String dataSource,
			String large_image_url, String image_url, String banner) {
		String adsPicture = "";
		if ("KP".equals(dataSource)) {
			adsPicture = "KP";
		} else if ("DianJing".equals(dataSource)) {
			adsPicture = "DJ";
		} else if ("ZiYunYing".equals(dataSource)) {
			adsPicture = "ZYY";
		} else if ("功能广告".equals(dataSource)) {
			adsPicture = "FUNC";
		} else if ("XinXiLiu".equals(dataSource)) {
			adsPicture = "XXL";
		} else if ("MediaV".equals(dataSource)) {
			adsPicture = "MV";
		} else if ("DianJingHongBao".equals(dataSource)) {
			adsPicture = "DJ";
		}
		return adsPicture;
	}

	public static String getdataSource(String adsPicture) {
		String dataSource = "";
		if ("DJ".equals(adsPicture)) {
			dataSource = "广告";
		} else if ("ZYY".equals(adsPicture)) {
			dataSource = "广告";
		} else if ("XXL".equals(adsPicture)) {
			dataSource = "信息流广告";
		} else if ("FUNC".equals(adsPicture)) {
			dataSource = "功能广告";
		} else if ("MV".equals(adsPicture)) {
			dataSource = "MV广告";
		}
		return dataSource;
	}

	public static List<String> getPkgname(String result) {
		List<String> pkgnameList = new ArrayList<String>();
		String pkgname = "";
		if (result.length() > 0) {
			if (result.length() > 0) {
				JSONObject jsonObject = JSONObject.fromObject(result);
				JSONArray style_array = jsonObject.getJSONArray("style");
				for (int i = 0; i < style_array.size(); i++) {
					JSONObject style_object = style_array.getJSONObject(i);
					JSONArray app_list_array = style_object
							.getJSONArray("app_list");
					for (int j = 0; j < app_list_array.size(); j++) {
						JSONObject app_list_object = app_list_array
								.getJSONObject(j);
						boolean flag = app_list_object.has("pkgname");
						if (flag) {
							pkgname = app_list_object.getString("pkgname");
							if (pkgname.length() > 0) {
								pkgnameList.add(pkgname);
							}
						}
					}
				}
			}
		}
		return pkgnameList;
	}

	public static List<Integer> getPkgnameSize(String result) {
		List<Integer> pkgnameSizeList = new ArrayList<Integer>();
		int pkgnameSize = 0;
		if (result.length() > 0) {
			if (result.length() > 0) {
				JSONObject jsonObject = JSONObject.fromObject(result);
				JSONArray style_array = jsonObject.getJSONArray("style");
				for (int i = 0; i < style_array.size(); i++) {
					JSONObject style_object = style_array.getJSONObject(i);
					JSONArray app_list_array = style_object
							.getJSONArray("app_list");
					for (int j = 0; j < app_list_array.size(); j++) {
						JSONObject app_list_object = app_list_array
								.getJSONObject(j);
						boolean flag = app_list_object.has("size");
						if (flag) {
							pkgnameSize = app_list_object.getInt("size");
							pkgnameSizeList.add(pkgnameSize);
						}
					}
				}
			}
		}
		return pkgnameSizeList;
	}

	public static List<String> styleVersionCodeFiled(String result) {
		List<String> styleFiledList = new ArrayList<String>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray style_array = jsonObject.getJSONArray("style");
			for (int i = 0; i < style_array.size(); i++) {
				JSONObject style_object = style_array.getJSONObject(i);
				JSONArray app_list_array = style_object
						.getJSONArray("app_list");
				for (int j = 0; j < app_list_array.size(); j++) {
					JSONObject app_list_object = app_list_array
							.getJSONObject(j);
					String pkgname = app_list_object.getString("pkgname");
					if (pkgname.length() > 0) {
						int r_id = app_list_object.getInt("r_id");
						if (r_id == 5) {
							String version_code = app_list_object
									.getString("version_code");
							if ("com.qihoo.appstore".equals(pkgname)
									&& version_code.length() <= 0) {
								styleFiledList.add("r_id:" + r_id + ",pkgname:"
										+ pkgname + "包名version_code为空,Fail");
							} else if ("com.qihoo.appstore".equals(pkgname)) {
								styleFiledList.add("r_id:" + r_id + ",pkgname:"
										+ pkgname + ",version_code:"
										+ version_code
										+ "包名version_code不为空,Pass");
							}
						}
						if (r_id == 3) {
							String version_code = app_list_object
									.getString("version_code");
							if (version_code.length() <= 0) {
								styleFiledList.add("r_id:" + r_id + ",pkgname:"
										+ pkgname + "包名version_code为空,Fail");
							}
						} else {
							// String
							// version_code=app_list_object.getString("version_code");
							// styleFiledList.add("r_id:"+r_id+",version_code:"+version_code);
						}

					}
				}
			}
		}
		return styleFiledList;
	}

	public static List<Integer> getStyleRID(String result) {
		List<Integer> r_idList = new ArrayList<Integer>();
		int r_id = 0;
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray style_array = jsonObject.getJSONArray("style");
			for (int i = 0; i < style_array.size(); i++) {
				JSONObject style_object = style_array.getJSONObject(i);
				int type = style_object.getInt("type");
				JSONArray app_list_array = style_object
						.getJSONArray("app_list");
				for (int j = 0; j < app_list_array.size(); j++) {
					JSONObject app_list_object = app_list_array
							.getJSONObject(j);
					r_id = app_list_object.getInt("r_id");
					r_idList.add(r_id);
					System.out.println(r_id);
					if (r_id == 11) {
						System.out.println("r_id:" + r_id);
						System.out.println("type:" + type);
					}
				}
			}
		}
		return r_idList;
	}

	public static List<Integer> news_style_type(String result) {
		List<Integer> r_idList = new ArrayList<Integer>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray news_style_array = jsonObject.getJSONArray("news_style");
			for (int i = 0; i < news_style_array.size(); i++) {
				JSONObject news_style_object = news_style_array.getJSONObject(i);
				int type = news_style_object.getInt("type");
				System.out.println(type);
				JSONArray news_list_jsonArray = news_style_object.getJSONArray("news_list");
				for (int j = 0; j < news_list_jsonArray.size(); j++) {
					JSONObject news_list_jsonObject = news_list_jsonArray.getJSONObject(j);
					String news_id = news_list_jsonObject.getString("news_id");
					if (news_id.length() == 0) {
						System.out.println("news_id下发为空");
					}
				}
				r_idList.add(type);
			}
		}
		return r_idList;
	}

	public static List<Integer> get_mv_style(String result) {
		List<Integer> list = new ArrayList<Integer>();
		if (result.length() > 0) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray mv_style_array = jsonObject.getJSONArray("mv_style");
			for (int i = 0; i < mv_style_array.size(); i++) {
				JSONObject mv_list_object = mv_style_array.getJSONObject(i);
				int type = mv_list_object.getInt("type");
				list.add(type);
				System.out.println(type);
			}
		}
		return list;
	}*/
}
