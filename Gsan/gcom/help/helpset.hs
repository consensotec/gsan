<?xml version='1.0' encoding='ISO-8859-1'?>
<!DOCTYPE helpset PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 2.0//EN"
                         "http://java.sun.com/products/javahelp/helpset_2_0.dtd">

<helpset version="2.0">
	<title>gcom</title>
	<maps>
		<homeID>gcom</homeID>
		<mapref location="map.xml"/>
	</maps>
	<view mergetype="javax.help.AppendMerge">
		<name>TOC</name>
		<label>Conte�do</label>
		<type>javax.help.TOCView</type>
		<data>toc.xml</data>
	</view>
	<view mergetype="javax.help.AppendMerge">
		<name>Index</name>
		<label>�ndice</label>
		<type>javax.help.IndexView</type>
		<data>index.xml</data>
	</view>
	<view>
		<name>Search</name>
		<label>Pesquisar</label>
		<type>javax.help.SearchView</type>
		<data engine="com.sun.java.help.search.DefaultSearchEngine">JavaHelpSearch</data>
	</view>
	
</helpset>
