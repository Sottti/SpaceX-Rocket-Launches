package com.spacex.launches.spacexrocketlaunches;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.spacex.launches.spacexrocketlaunches.databinding.RocketListBinding;

public class RocketList extends AppCompatActivity {

  private RocketListBinding viewBinding;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, RocketList.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = DataBindingUtil.setContentView(this, R.layout.rocket_list);
  }
}
