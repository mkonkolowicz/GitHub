Private Sub CommandButton1_Click()
    Dim desiredfileName
    desiredfileName = Sheets("CNS Weekly Report").Range("Z2").Value
    
    Dim fileName
    fileName = Application.GetSaveAsFilename(desiredfileName, FileFilter:="Excel Files (*.XLSM), *.XLSM", Title:="Save As")
    
    If fileName = False Then
        Exit Sub
    End If
    
    On Error Resume Next
    ActiveWorkbook.SaveAs fileName:=desiredfileName
        
    If Err.Number = 1004 Then
        Exit Sub
    End If
    
End Sub

Private Sub SaveButton_Click()
    Dim desiredfileName
    desiredfileName = Sheets("CNS Weekly Report").Range("Z2").Value
    
    If desiredfileName = False Then
        Dim fileName
        fileName = Application.GetSaveAsFilename(desiredfileName, FileFilter:="Excel Files (*.XLSM), *.XLSM", Title:="Save As")
    End If
    
    On Error Resume Next
    ActiveWorkbook.Save
    
    If Err.Number = 1004 Then
        Exit Sub
    End If

End Sub
