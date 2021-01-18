import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
    selector: 'confirmation-dialog-component',
    templateUrl: './confirmation-dialog.component.html',
    styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {
    title: string;
    message: string;
    textConfirm: string;
    textDismiss: string;

    constructor(private dialogRef: MatDialogRef<ConfirmationDialogComponent>, @Inject(MAT_DIALOG_DATA) data: ConfirmationDialogModel) {
        this.title = data.title || 'Confirm Deletion';
        this.message = data.message;
        this.textConfirm = data.confirm || 'Delete';
        this.textDismiss = data.dismiss || 'Abort';
    }

    onConfirm(): void {
        this.dialogRef.close(true);
    }

    onDismiss(): void {
        this.dialogRef.close(false);
    }
}

export class ConfirmationDialogModel {
    constructor(public title: string, public message: string, public confirm: string, public dismiss: string) {
    }
}
