package kr.pe.courage.common.utils;

public class TestTag {
	public static void main(String[] args) {
		String tag = "<script></script><iframe></iframe>";
		
		System.out.println(tag.contains("<script"));
		System.out.println(tag.contains("<iframe"));
	}
}
