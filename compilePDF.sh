#!/bin/bash



cd epflLectureNotes/epflLectureNotes

git pull origin

pdflatex -interaction=nonstopmode -aux-directory=auxFiles -output-directory=analysis analysis/analysis.tex

pdflatex -interaction=nonstopmode -aux-directory=<auxFiles> -output-directory=<analysis> advancedComputation/aicc.tex

pdflatex -interaction=nonstopmode -aux-directory=<auxFiles> -output-directory=<analysis> linearAlgerba/linearAlgebra.tex


git add .
git commit -m "pdf update"
git push origin 
