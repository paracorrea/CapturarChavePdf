# CapturarChavePdf
#
#
### UAU!!! This project is real!!! 
# This is a real project. I made it at the request of a company that works with official documents. In Brazil, all purchases are preceded by an invoice, called Nota Fiscal, in this project the invoices were stored within PDF files. So, we made a method to read all found pdf files from a folder and generated a txt file with the name of the pdf files.
# Then another method, reads the txt, line by line and opens the pdf with the Java PDF BOX API and generates from each page found a jpg image in another folder and also generates a second txt file containing the file path and name of the image, with the image name containing the PDF page number for differentiation.
# Another method is then executed and now it reads the txt, takes the image file and with the Zxing API captures the barcode of the invoice. Capturing the document key. As an output, we generate another txt, now with the file name and the captured barcode. However, many documents do not have the information or the tool cannot read the code. So we have another method that now OCR the image, in the files that were not captured and through a regular expression, get the converted code and finally update the final txt that goes to the client.

