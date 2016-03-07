package com.example.budgetkeeper;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class barGraph {
	
	int[] amount_months = new int[12];
	
	public barGraph(int[] temp)
	{
		for(int i = 0; i<12; i++)
		{
			amount_months[i] = temp[i];
		}
	}
	
	public Intent getIntent(Context context)
	{
		//int[] y = {4500, 3300, 2222, 5454, 2222, 900, 5555, 3332, 1000, 4000, 300, 5000};
		
		int[] colors = new int[]{Color.rgb(116, 192, 205)};
		
		CategorySeries series = new CategorySeries("Yearly Spendings");
		for(int i = 0; i < amount_months.length; i++)
		{
			series.add("Bar " + (i+1), amount_months[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
					
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		
		mRenderer.setXLabels(1);
		mRenderer.setYLabels(0);
		mRenderer.setLabelsTextSize(40);
		mRenderer.addXTextLabel(1, "Jan");
		mRenderer.addXTextLabel(2, "Feb");
		mRenderer.addXTextLabel(3, "Mar");
		mRenderer.addXTextLabel(4, "Apr");
		mRenderer.addXTextLabel(5, "May");
		mRenderer.addXTextLabel(6, "Jun");
		mRenderer.addXTextLabel(7, "Jul");
		mRenderer.addXTextLabel(8, "Aug");
		mRenderer.addXTextLabel(9, "Sep");
		mRenderer.addXTextLabel(10, "Oct");
		mRenderer.addXTextLabel(11, "Nov");
		mRenderer.addXTextLabel(12, "Dec");
		mRenderer.setXRoundedLabels(true);
		
		
		renderer.setDisplayChartValues(true);
		
	
		renderer.setChartValuesSpacing(20);
		renderer.setChartValuesTextAlign(Align.CENTER);
		renderer.setChartValuesTextSize((float) 50.5);
		renderer.setColor(Color.rgb(116, 192, 205));
		
		
		mRenderer.setLegendTextSize(30);
		mRenderer.setBarSpacing(5);
		mRenderer.setBarWidth(40);
		
		
			
		Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
		
		return intent;
	}

}
