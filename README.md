## Final Report Files Organizer

A local pull request (PR) is created for each pertaining issue so that it is easier for the TA grader to get an accumulated resource of files changed, commit history, and more. Please reference it!

### Issue 615

Local PR
* https://github.com/abhishekshinde21/OpenPDF/pull/2

Static Analysis
* https://github.com/abhishekshinde21/OpenPDF/blob/final-report/static-analysis/HtmlWorker-before.html
* https://github.com/abhishekshinde21/OpenPDF/blob/final-report/static-analysis/HtmlWorker-after.html

### Issue 615

Local PR
* https://github.com/abhishekshinde21/OpenPDF/pull/1

Static Analysis
* https://github.com/abhishekshinde21/OpenPDF/blob/final-report/static-analysis/ColumnText-before.html
* https://github.com/abhishekshinde21/OpenPDF/blob/final-report/static-analysis/ColumnText-after.html
* https://github.com/abhishekshinde21/OpenPDF/blob/final-report/static-analysis/PdfDocument-before.html
* https://github.com/abhishekshinde21/OpenPDF/blob/final-report/static-analysis/PdfDocument-after.html

### Issue 373

Commit
* https://github.com/abhishekshinde21/OpenPDF/commit/1311704c642be5972c8f43df91981a816f472602

Notes
- Current Behavior: The table intended to be placed in the footer gets improperly set near the top of the page. Here is the PDF output:

- Correct Behavior: The correct behavior is that a table component placed in the footer section of the PDF document should be located at the bottom of the respective page.

- Progress:
 
  •	Understood that the observer design pattern is followed such that the PdfDocument instances listen to changes and get updated by the Document instance

  •	Functionality for adding a String to the footer behaves differently than more complex types, such as image and table

  •	Document.open() informs listeners to update
  
  • The footer has to be set beforehand (i.e. footer cannot be set after the document is opened)

  •	PdfDocument instance populates the table accordingly by the dimensions specified and where the current cursor is 

