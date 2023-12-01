package emke.comp2161.tictactoeapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class PlayerNumberDialog extends AppCompatDialogFragment {
    private Button one;
    private Button two;

    @NonNull
    @Override
    //Creates a dialog prompting user to select how many players are playing
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_players, null);

        builder.setView(view);

        one = (Button) view.findViewById(R.id.onePlayer);
        two = (Button) view.findViewById(R.id.twoPlayer);

        //Click listener for 1 player button
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calls function to set up 1 player game from main activity
                if(getActivity() instanceof MainActivity){
                    getDialog().dismiss();
                    ((MainActivity)getActivity()).openOnePlayerNameDialog();
                }
            }
        });

        //Click listener for 2 player button
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calls function to set up 2 player game from main activity
                if(getActivity() instanceof MainActivity){
                    getDialog().dismiss();
                    ((MainActivity)getActivity()).openTwoPlayerNameDialog();
                }
            }
        });

        return builder.create();
    }
}
