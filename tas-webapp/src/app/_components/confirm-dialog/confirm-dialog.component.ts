import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent> ) { }

  /**
   * Informa que a janela foi fechada pelo CANCELAR ("false" na funçao close)
   */  
  public cancelar(): void {
    this.dialogRef.close(false);
  }

  /**
   * Informa que a janela foi fechada pelo CONFIRMAR ("true" na funçao close)
   */  
  public confirmar(): void {
    this.dialogRef.close(true);
  }
}
