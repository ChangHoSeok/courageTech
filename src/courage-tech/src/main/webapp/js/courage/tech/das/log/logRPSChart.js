var LogRPSChart = {
	MENU_NO : "",
	FORM_ID : "formLogRPSChart",
	DETAIL_ID : "retrieveLogRPSChart",
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
		$jquery("#menuNavi").html(Navi.getNaviNm(LogRPSChart.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogRPSChart.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogRPSChart.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + LogRPSChart.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
		
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
	            text: 'RPS View'
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
	                text: 'Request'
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
	                        return Highcharts.dateFormat("%H:%M:%S", this.x) + "<br />" + this.y + " RPS";
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
		if (LogRPSChart._formValidate()) {
			LogRPSChart.chartRefresh();
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
		if ($jquery("#" + LogRPSChart.FORM_ID).validationEngine('validate')) {
			return true;
		} else {
			return false;
		}
	},
	
	chartRefresh : function() {
		var chart = $jquery("#chartView").highcharts();
		
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + "/das/log/retrieveLogRPSChartData.tech",
			data	: $jquery("#" + LogRPSChart.FORM_ID).separator("separatorRemoveForm").serialize(),
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
				$jquery("#" + LogRPSChart.FORM_ID).separator("separatorAddForm");
			},
			async	: false
		});
	}
};