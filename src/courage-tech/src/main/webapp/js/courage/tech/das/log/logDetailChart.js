var LogDetailChart = {
	MENU_NO : "",
	FORM_ID : "formLogDetailChart",
	DETAIL_ID : "retrieveLogDetailChart",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(LogDetailChart.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogDetailChart.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogDetailChart.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + LogDetailChart.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
		
		// Slider 초기화
		$jquery("#" + LogDetailChart.FORM_ID + " #timeSlider").slider({
			range : true,
			min: 0,
			max: 24,
			values: [0, 5],
			slide: function(event, ui) {
				$jquery("#" + LogDetailChart.FORM_ID + " #timeValue").html(ui.values[0] + "시 - " + ui.values[1] + "시");
			}
		});
		
		$jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider({
			range : true,
			min: 0,
			max: 10000,
			values: [400, 1500],
			step: 100,
			slide: function(event, ui) {
				$jquery("#" + LogDetailChart.FORM_ID + " #PTValue").html(ui.values[0] + " ms - " + ui.values[1] + " ms");
			}
		});

		$jquery("#" + LogDetailChart.FORM_ID + " #timeValue").html(
				$jquery("#" + LogDetailChart.FORM_ID + " #timeSlider").slider("values", 0) + "시 - "
				+ $jquery("#" + LogDetailChart.FORM_ID + " #timeSlider").slider("values", 1) + "시");
		
		$jquery("#" + LogDetailChart.FORM_ID + " #PTValue").html(
				$jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider("values", 0) + " ms - "
				+ $jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider("values", 1) + " ms");
		
		// Chart 초기화
		Highcharts.setOptions({
	        global : {
	            useUTC : false
	        }
	    });
		
		$jquery("#chartView").highcharts({
	        chart: {
	            type: 'scatter',
	            zoomType: 'xy',
	            height: 500
	        },
	        title: {
	            text: 'Process View'
	        },
	        xAxis: {
	            title: {
	                text: 'Time'
	            },
	            type: "datetime",
	            labels: {
	                formatter : function() {
	                    return Highcharts.dateFormat('%H:%M', this.value);
	                }
	            }
	        },
	        yAxis: {
	        	min: 0,
	            title: {
	                text: 'Process TimeMillis (ms)'
	            }
	        },
	        plotOptions: {
	            scatter: {
	                marker: {
	                    radius: 3,
	                    states: {
	                        hover: {
	                            enabled: true,
	                            lineColor: 'rgb(100,100,100)'
	                        }
	                    }
	                },
	                states: {
	                    hover: {
	                        marker: {
	                            enabled: false
	                        }
	                    }
	                },
	                tooltip: {
	                    pointFormatter: function() {
	                        return Highcharts.dateFormat("%H:%M:%S", this.x) + "<br />" + this.y + "ms";
	                    }
	                }
	            }
	        },
	        credits : {
	        	enabled : false
	        }
	    });
	},
	
	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	search : function(thisObj) {
		if (LogDetailChart._formValidate()) {
			$jquery("#" + LogDetailChart.FORM_ID + " #condStartHour").val(lpad($jquery("#" + LogDetailChart.FORM_ID + " #timeSlider").slider("values", 0), 2, "0"));
			$jquery("#" + LogDetailChart.FORM_ID + " #condEndHour").val(lpad($jquery("#" + LogDetailChart.FORM_ID + " #timeSlider").slider("values", 1), 2, "0"));

			$jquery("#" + LogDetailChart.FORM_ID + " #condLimitPTStart").val($jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider("values", 0));
			$jquery("#" + LogDetailChart.FORM_ID + " #condLimitPTEnd").val($jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider("values", 1));
			
			LogDetailChart.chartRefresh();
		}
	},
	
	/**
	 * 개요 : 로그인 form Validate
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 29.
	 * @param 
	 */
	_formValidate : function() {
		if ($jquery("#" + LogDetailChart.FORM_ID).validationEngine('validate')) {
			return true;
		} else {
			return false;
		}
	},
	
	chartRefresh : function() {
		var chart = $jquery("#chartView").highcharts();
		
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + "/das/log/retrieveLogDetailChartData.tech",
			data	: $jquery("#" + LogDetailChart.FORM_ID).separator("separatorRemoveForm").serialize(),
			dataType: "json",
			success	: function (data) {
				if (data.actionStatus == "success") {
					// Chart 삭제
					while (chart.series.length > 0) {
						chart.series[0].remove(false);
					}
					
					// Chart 정보 등록
					for (var i = 0; i < data.seriesList.length; i++) {
						chart.addSeries({
							name: data.seriesList[i],
							color: "rgb(124, 181, 236)",
							marker: {symbol: "circle"},
							data: data.seriesDataList[i]
						}, false);
					}
				} else {
					if (data.actionMessage !== "") {
						alert(data.actionMessage);
					} else {
						alert("저장처리를 정상적으로 완료하지 못했습니다.");
					}
				}
			},
			error	: function(x, e) {
				alert("오류가 발생되었습니다.");
			},
			complete	: function() {
				chart.redraw();
				$jquery("#" + LogDetailChart.FORM_ID).separator("separatorAddForm");
			},
			async	: false
		});
	},
	
	/**
	 * 개요 : 조회제한 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 29.
	 * @param 
	 */
	switchLimit : function(thisObj) {
		if ($jquery(thisObj).prop("checked")) {
			$jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider("option", "disabled", true);
		} else {
			$jquery("#" + LogDetailChart.FORM_ID + " #PTSlider").slider("option", "disabled", false);
		}
	}
};