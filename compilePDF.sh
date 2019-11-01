#!/bin/bash



cd epflLectureNotes/epflLectureNotes

git pull origin

pdflatex -interaction=nonstopmode -aux-directory=<auxFiles> -output-directory=<analysis> analysis/analysis.tex

git add .
git commit -m "pdf update"
git push origin 
