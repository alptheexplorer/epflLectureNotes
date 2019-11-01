#!/bin/bash



cd epflLectureNotes/epflLectureNotes


pdflatex analysis/analysis.tex -interaction=nonstopmode 

git pull origin



git add .
git commit -m "pdf update"
git push origin 
