#!/bin/bash



cd epflLectureNotes/epflLectureNotes

git pull origin

pdflatex -interaction=nonstopmode analysis/analysis.tex
pdflatex -interaction=nonstopmode advancedComputation/aicc.tex
pdflatex -interaction=nonstopmode linearAlgebra/linearAlgebra.tex

git add .
git commit -m "pdf update"
git push origin 
