#!/bin/bash



cd epflLectureNotes/epflLectureNotes


pdflatex -interaction=nonstopmode analysis/analysis.tex

git pull origin



git add .
git commit -m "pdf update"
git push origin 
