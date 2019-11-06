#!/bin/bash


echo $"Im about to pull from origin"
echo $"press e to proceed"
read userResponse

if[$userResponse == "e"] 
	then
		cd Desktop/epflLectureNotes/epflLectureNotes/analysis
		pdflatex analysis.latex -interaction=nonstopmode
	
	else
		echo$"goodbye"

fi


