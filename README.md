To generate a study guide, enter the following as arguments to Driver
- Path which contains all markdown files you'd like to be exported 
- How you would like the content to be sorted by : "filename", "modified", or "created"
- Path to where the studyguide should be stored (must be a .md)
The study guide will contain all headings marked by #, ##, ###, or #### and all important info contained within 
double brackets [[]]
Additionally, all questions in the format [[question :: answer]] will be output to a file of the same path of the
studyguide, but with .sr rather than .md
[output.png](src/main/resources/screenshots/output.png)

To review these questions later, run driver with no arguments. You will be prompted for the path to a .sr file
[questionFile.png](src/main/resources/screenshots/questionFile.png)